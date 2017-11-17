package com.xatskevich.pzhsnb;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private static final String NAME = "Pozhsnab";
    private BluetoothDevice device = null;
    private static final String TAG = "bluetooth1";
    ArrayAdapter<String> mArrayAdapter = null;
    private static String devAddress;
    private static String devName;
    public static TextView mText, devText;
    private Button button_connect;
    private Button button_config;
    private Button button_diag;
    //public static boolean connect_state;
    public boolean reg_state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //    if ( savedInstanceState == null ) launch_state = false;
        button_connect = (Button) findViewById(R.id.button_connect);
        button_connect.setText("Подключить");

        Connect.btAdapter = BluetoothAdapter.getDefaultAdapter();       // получаем локальный Bluetooth адаптер
        checkBTState();

        mText = (TextView) findViewById(R.id.textView);
        devText = (TextView) findViewById(R.id.devText);


        Connect.mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                //mText.setText(Connect.message);
                if(Connect.buffer[0] == -18){
                    button_connect.setText("Отключить");
                    devText.setText(devName);
                }

            }
        };//*/
    }

    private void checkBTState() {
        String status;
        // Проверяем адаптер
        if(Connect.btAdapter==null) {
            errorExit("Error", "Bluetooth не поддерживается");
        } else {
            if (Connect.btAdapter.isEnabled()) {
                //Log.d(TAG, "...Bluetooth включен...");
                String mydeviceaddress= Connect.btAdapter.getAddress();
                String mydevicename= Connect.btAdapter.getName();
                Toast.makeText(getBaseContext(), mydevicename, Toast.LENGTH_SHORT).show();
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(Connect.btAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, Connect.REQUEST_ENABLE_BT);
            }
        }
    }

    //приемник списка устройств
    final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };

    private void errorExit(String title, String message){
        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        Connect.mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                // mText.setText(Connect.message);
                if(Connect.buffer[0] == -18){
                    button_connect.setText("Отключить");
                    devText.setText(devName);
                }
            }
        };
    }

    public void onDestroy() {
        super.onDestroy();
        try     {
            if(reg_state) unregisterReceiver(mReceiver);
            reg_state = false;
            if(Connect.btSocket != null) Connect.btSocket.close();
        } catch (IOException e2) {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }
    }//*/

    @Override
    public void onPause() {
        super.onPause();

    }

    //нажатие на кнопки
    public void onClick(final View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        mArrayAdapter = new ArrayAdapter<String>(this, //R.layout.list_item);
                android.R.layout.select_dialog_item);


        switch (view.getId()){
            case R.id.button_config:    //кнопка конфигурации
                //Toast.makeText(getBaseContext(), "Конфигурация", Toast.LENGTH_SHORT).show();
                Intent intent_config = new Intent(MainActivity.this, Config.class);             //переход к экрану конфигурации
                startActivity(intent_config);
                //Connect.ConnectedThread.write("R");
                break;
            case R.id.button_connect:
                String str = (String) button_connect.getText();
                if(button_connect.getText() == "Подключить") {
                    Connect.btAdapter.startDiscovery();
//
                    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
                    reg_state = true;

                    builder.setTitle("Поиск устройств...")
                            .setAdapter(mArrayAdapter, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int item) {

                                    String tmpDev = mArrayAdapter.getItem(item);
                                    int t = tmpDev.indexOf("\n");
                                    devName = tmpDev.substring(0, t);
                                    devAddress = tmpDev.substring(t + 1);
                                    Toast.makeText(getBaseContext(), devName, Toast.LENGTH_LONG).show();

                                    device = Connect.btAdapter.getRemoteDevice(devAddress);
                                    try {
                                        Connect.btSocket = device.createRfcommSocketToServiceRecord(Connect.MY_UUID);
                                    } catch (IOException e) {
                                        errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
                                    }

                                    Connect.btAdapter.cancelDiscovery();

                                    try {
                                        Connect.btSocket.connect();
                                    } catch (IOException e) {
                                        try {
                                            Connect.btSocket.close();
                                        } catch (IOException e2) {
                                            errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
                                        }
                                    }

                                    // button_connect.setText("Отключить");
                                    // devText.setText(devName);

                                    Connect.mConnectedThread = new Connect.ConnectedThread(Connect.btSocket);
                                    Connect.mConnectedThread.start();

                                    if(Connect.btSocket != null){
                                        Connect.ConnectedThread.write(0xEE, 1);  //проверка подключения
                                    }
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setNegativeButton("Отмена", null)
                            .create();
                    builder.show();


                }
                if(button_connect.getText() == "Отключить"){
                    button_connect.setText("Подключить");
                    devText.setText("Не подключен");
                    try     {
                        if(reg_state) unregisterReceiver(mReceiver);
                        reg_state = false;
                        if(Connect.btSocket != null) Connect.btSocket.close();
                    } catch (IOException e2) {

                    }
                }
                break;
            case R.id.button_diag:
                //Toast.makeText(getBaseContext(), "Диагностика", Toast.LENGTH_SHORT).show();
                //Intent intent_diag = new Intent(MainActivity.this, Diag.class);
                //startActivity(intent_diag);

                char b = 78;
                //Arrays.toString(b);
                Toast.makeText(getBaseContext(), String.valueOf(b), Toast.LENGTH_SHORT).show();
                if(Connect.btSocket != null) {
                    Connect.ConnectedThread.write('N', 4);
                    // Log.d("", "Buffer = "+ b.toString());
                }
                break;
        }
    }

}