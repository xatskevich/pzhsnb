package com.xatskevich.pzhsnb;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Hatskevich on 21.06.2016.
 */
public class Connect {
    public static Handler mHandler = new Handler();
    public static final int REQUEST_ENABLE_BT = 1;
    public static final int RECEIVE_MESSAGE = 1;        // Статус для Handler
    public static BluetoothAdapter btAdapter = null;
    public static BluetoothSocket btSocket = null;
    public static StringBuilder sb = new StringBuilder();
    public static String message;
    public static int receive_bytes;
    public static byte send_code;
    public static Byte b1;
    public static byte[] buffer;

    public static ConnectedThread mConnectedThread;
    // SPP UUID сервиса
    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // MAC-адрес Bluetooth модуля

    public static class ConnectedThread extends Thread {
        public static BluetoothSocket mmSocket;
        public static InputStream mmInStream;
        public static OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }



        public void run() {
           // byte[] buffer;// = new byte[32];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    bytes = mmInStream.available();
                    if (bytes < receive_bytes || bytes == 0)
                    {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block

                        }
                        continue;
                    }
                    buffer = new byte[bytes];  // buffer store for the stream

                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);        // Получаем кол-во байт и само собщение в байтовый массив "buffer"
                    //Log.d("", "Buffer ="+ Arrays.toString(buffer));
                   // message = String.valueOf(bytes);
                    b1 = buffer[0];
                   // Byte b2 = buffer[2];
                   // int i = b1.intValue()*10-528+b2.intValue();
                    //message = Arrays.toString(buffer);
                    message = Arrays.toString(buffer);
                    //txtArduino.setText(str);


                    mHandler.obtainMessage(RECEIVE_MESSAGE, bytes, -1, buffer).sendToTarget();     // Отправляем в очередь сообщений Handler
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public static void write(int message, int bytes) {

            //byte[] msgBuffer = message.getBytes();
            receive_bytes = bytes;
           // Toast.makeText(getBaseContext(), String.valueOf(Connect.b1), Toast.LENGTH_SHORT).show();
            try {
                mmOutStream.write(message);
            } catch (IOException e) {

            }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }
}
