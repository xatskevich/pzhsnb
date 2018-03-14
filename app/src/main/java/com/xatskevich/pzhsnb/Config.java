package com.xatskevich.pzhsnb;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * Created by Hatskevich on 13.06.2016.
 */
public class Config extends AppCompatActivity {

    private Button btnCab;
    private Button btnBody1;
    private Button btnBody2;
    private Button btnBody3;
    private Button btnBody4;
    private Button btnBody5;
    private Button btnBody6;
    private Button btnBody7;
    private Button btnBody8;
    private Button btnBody9;
    private LinearLayout layoutPower1, layoutPower2, layoutPower3, layoutPower4, layoutPower5, layoutPower6,
            layoutPower7, layoutPower8, layoutPower9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        btnBody1 = (Button) findViewById(R.id.btnBody1);
        btnBody2 = (Button) findViewById(R.id.btnBody2);
        btnBody3 = (Button) findViewById(R.id.btnBody3);
        btnBody4 = (Button) findViewById(R.id.btnBody4);
        btnBody5 = (Button) findViewById(R.id.btnBody5);
        btnBody6 = (Button) findViewById(R.id.btnBody6);
        btnBody7 = (Button) findViewById(R.id.btnBody7);
        btnBody8 = (Button) findViewById(R.id.btnBody8);
        btnBody9 = (Button) findViewById(R.id.btnBody9);
        layoutPower1 = (LinearLayout) findViewById(R.id.layoutPower1);
        layoutPower2 = (LinearLayout) findViewById(R.id.layoutPower2);
        layoutPower3 = (LinearLayout) findViewById(R.id.layoutPower3);
        layoutPower4 = (LinearLayout) findViewById(R.id.layoutPower4);
        layoutPower5 = (LinearLayout) findViewById(R.id.layoutPower5);
        layoutPower6 = (LinearLayout) findViewById(R.id.layoutPower6);
        layoutPower7 = (LinearLayout) findViewById(R.id.layoutPower7);
        layoutPower8 = (LinearLayout) findViewById(R.id.layoutPower8);
        layoutPower9 = (LinearLayout) findViewById(R.id.layoutPower9);


    }

    @Override
    public void onResume() {
        super.onResume();

        findViewById(R.id.load).setVisibility(View.VISIBLE);
        findViewById(R.id.base).setVisibility(View.GONE);

        if(Connect.btSocket != null){
            Connect.ConnectedThread.write(0xAE, 10);  //запрос неадресованных модулей
        }///*/

        Connect.mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                findViewById(R.id.load).setVisibility(View.GONE);
                findViewById(R.id.base).setVisibility(View.VISIBLE);
            /*    btnBody1.setText("СИЛОВОЙ МОДУЛЬ СЛЕВА 1\nне сконфигурирован");
                btnBody1.setVisibility(View.GONE);
                btnBody2.setText("СИЛОВОЙ МОДУЛЬ СЛЕВА 2\nне сконфигурирован");
                btnBody2.setVisibility(View.GONE);
                btnBody3.setText("СИЛОВОЙ МОДУЛЬ СЛЕВА 3\nне сконфигурирован");
                btnBody3.setVisibility(View.GONE);
                btnBody4.setText("СИЛОВОЙ МОДУЛЬ СЛЕВА 4\nне сконфигурирован");
                btnBody4.setVisibility(View.GONE);
                btnBody5.setText("СИЛОВОЙ МОДУЛЬ СПРАВА 1\nне сконфигурирован");
                btnBody5.setVisibility(View.GONE);
                btnBody6.setText("СИЛОВОЙ МОДУЛЬ СПРАВА 2\nне сконфигурирован");
                btnBody6.setVisibility(View.GONE);
                btnBody7.setText("СИЛОВОЙ МОДУЛЬ СПРАВА 3\nне сконфигурирован");
                btnBody7.setVisibility(View.GONE);
                btnBody8.setText("СИЛОВОЙ МОДУЛЬ СПРАВА 4\nне сконфигурирован");
                btnBody8.setVisibility(View.GONE);
                btnBody9.setText("СИЛОВОЙ МОДУЛЬ ЗАДНИЙ\nне сконфигурирован");
                btnBody9.setVisibility(View.GONE);*/
                for(int i=0; i<Connect.buffer.length; i++){     //проверка адресованных модулей
                    if(Connect.buffer[i] == -1){

                    } else if(Connect.buffer[i] == 0x50){
                        //btnBody1.setVisibility(View.VISIBLE);
                        layoutPower1.setVisibility(View.VISIBLE);
                    } else if(Connect.buffer[i] == 0x51){
                        //btnBody2.setVisibility(View.VISIBLE);
                        layoutPower2.setVisibility(View.VISIBLE);
                    } else if(Connect.buffer[i] == 0x52){
                        //btnBody3.setVisibility(View.VISIBLE);
                        layoutPower3.setVisibility(View.VISIBLE);
                    } else if(Connect.buffer[i] == 0x53){
                        //btnBody4.setVisibility(View.VISIBLE);
                        layoutPower4.setVisibility(View.VISIBLE);
                    } else if(Connect.buffer[i] == 0x60){
                        //btnBody5.setVisibility(View.VISIBLE);
                        layoutPower5.setVisibility(View.VISIBLE);
                    } else if(Connect.buffer[i] == 0x61){
                        //btnBody6.setVisibility(View.VISIBLE);
                        layoutPower6.setVisibility(View.VISIBLE);
                    } else if(Connect.buffer[i] == 0x62){
                        //btnBody7.setVisibility(View.VISIBLE);
                        layoutPower7.setVisibility(View.VISIBLE);
                    } else if(Connect.buffer[i] == 0x63){
                        //btnBody8.setVisibility(View.VISIBLE);
                        layoutPower8.setVisibility(View.VISIBLE);
                    } else if(Connect.buffer[i] == 0x56){
                        //btnBody9.setVisibility(View.VISIBLE);
                        layoutPower9.setVisibility(View.VISIBLE);
                    }
                }
            }
        };
    }

    public void onDestroy() {
        super.onDestroy();

    }//*/

    @Override
    public void onPause() {
        super.onPause();

    }

    public void onClick(View view) {
        String send;
        switch (view.getId()){  //выбор кнопки для адресации
            case R.id.btnSys:
                if(Connect.btSocket != null){
                    Intent intent_config_system = new Intent(Config.this, Config_system.class);             //переход к экрану конфигурации системы
                    startActivity(intent_config_system);
                }
                break;
            case R.id.btnBody1:
                if(Connect.btSocket != null){
                    Intent intent_config_power = new Intent(Config.this, ConfigPower.class);             //переход к экрану конфигурации силового
                    intent_config_power.putExtra("address", 0x50);
                    startActivity(intent_config_power);
                }
                break;
            case R.id.btnBody2:
                if(Connect.btSocket != null){
                    Intent intent_config_power = new Intent(Config.this, ConfigPower.class);             //переход к экрану конфигурации силового
                    intent_config_power.putExtra("address", 0x51);
                    startActivity(intent_config_power);
                }
                break;
            case R.id.btnBody3:
                if(Connect.btSocket != null){
                    Intent intent_config_power = new Intent(Config.this, ConfigPower.class);             //переход к экрану конфигурации силового
                    intent_config_power.putExtra("address", 0x52);
                    startActivity(intent_config_power);
                }
                break;
            case R.id.btnBody4:
                if(Connect.btSocket != null){
                    Intent intent_config_power = new Intent(Config.this, ConfigPower.class);             //переход к экрану конфигурации силового
                    intent_config_power.putExtra("address", 0x53);
                    startActivity(intent_config_power);
                }
                break;
            case R.id.btnBody5:
                if(Connect.btSocket != null){
                    Intent intent_config_power = new Intent(Config.this, ConfigPower.class);             //переход к экрану конфигурации силового
                    intent_config_power.putExtra("address", 0x60);
                    startActivity(intent_config_power);
                }
                break;
            case R.id.btnBody6:
                if(Connect.btSocket != null){
                    Intent intent_config_power = new Intent(Config.this, ConfigPower.class);             //переход к экрану конфигурации силового
                    intent_config_power.putExtra("address", 0x61);
                    startActivity(intent_config_power);
                }
                break;
            case R.id.btnBody7:
                if(Connect.btSocket != null){
                    Intent intent_config_power = new Intent(Config.this, ConfigPower.class);             //переход к экрану конфигурации силового
                    intent_config_power.putExtra("address", 0x62);
                    startActivity(intent_config_power);
                }
                break;
            case R.id.btnBody8:
                if(Connect.btSocket != null){
                    Intent intent_config_power = new Intent(Config.this, ConfigPower.class);             //переход к экрану конфигурации силового
                    intent_config_power.putExtra("address", 0x63);
                    startActivity(intent_config_power);
                }
                break;
            case R.id.btnBody9:
                if(Connect.btSocket != null){
                    Intent intent_config_power = new Intent(Config.this, ConfigPower.class);             //переход к экрану конфигурации силового
                    intent_config_power.putExtra("address", 0x56);
                    startActivity(intent_config_power);
                }
                break;
        }


    }




    }
