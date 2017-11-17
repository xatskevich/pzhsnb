package com.xatskevich.pzhsnb;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ConfigKBR extends AppCompatActivity {

    private static RadioGroup rgIn1Type, rgIn1Side, rgIn2Type, rgIn2Side, rgOut1Type, rgOut2Type, rgOut3Type;
    private RadioButton rbIn1Left, rbIn1Right, rbIn1On, rbIn1Off, rbIn2Left, rbIn2Right, rbIn2On, rbIn2Off,
                        rbOut1Light, rbOut1Tail, rbOut2Left, rbOut2Right, rbOut3Left, rbOut3Right, rbOut3Tail;
    public static CheckBox cbIn1, cbIn2, cbOut1, cbOut2, cbOut3;
    public static LinearLayout layoutIn1, layoutIn2, layoutOut1, layoutOut2, layoutOut3;
    public static Button btnClr, btnSave;
    private char conf[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_kbr);

        btnClr = (Button) findViewById(R.id.btnClr);
        btnSave = (Button) findViewById(R.id.btnSave);
        rgIn1Type = (RadioGroup) findViewById(R.id.rgIn1Type);
        rgIn1Side = (RadioGroup) findViewById(R.id.rgIn1Side);
        rgIn2Type = (RadioGroup) findViewById(R.id.rgIn2Type);
        rgIn2Side = (RadioGroup) findViewById(R.id.rgIn2Side);
        rgOut1Type = (RadioGroup) findViewById(R.id.rgOut1Type);
        rgOut2Type = (RadioGroup) findViewById(R.id.rgOut2Type);
        rgOut3Type = (RadioGroup) findViewById(R.id.rgOut3Type);
        rbIn1Left = (RadioButton) findViewById(R.id.rbIn1Left);
        rbIn1Right = (RadioButton) findViewById(R.id.rbIn1Right);
        rbIn1On = (RadioButton) findViewById(R.id.rbIn1On);
        rbIn1Off = (RadioButton) findViewById(R.id.rbIn1Off);
        rbIn2Left = (RadioButton) findViewById(R.id.rbIn2Left);
        rbIn2Right = (RadioButton) findViewById(R.id.rbIn2Right);
        rbIn2On = (RadioButton) findViewById(R.id.rbIn2On);
        rbIn2Off = (RadioButton) findViewById(R.id.rbIn2Off);
        rbOut1Light = (RadioButton) findViewById(R.id.rbOut1Light);
        rbOut1Tail = (RadioButton) findViewById(R.id.rbOut1Tail);
        rbOut2Left = (RadioButton) findViewById(R.id.rbOut2Left);
        rbOut2Right = (RadioButton) findViewById(R.id.rbOut2Right);
        rbOut3Left = (RadioButton) findViewById(R.id.rbOut3Left);
        rbOut3Right = (RadioButton) findViewById(R.id.rbOut3Right);
        rbOut3Tail = (RadioButton) findViewById(R.id.rbOut3Tail);
        cbIn1 = (CheckBox) findViewById(R.id.cbIn1);
        cbIn2 = (CheckBox) findViewById(R.id.cbIn2);
        cbOut1 = (CheckBox) findViewById(R.id.cbOut1);
        cbOut2 = (CheckBox) findViewById(R.id.cbOut2);
        cbOut3 = (CheckBox) findViewById(R.id.cbOut3);
        layoutIn1 = (LinearLayout) findViewById(R.id.layoutIn1);
        layoutIn2 = (LinearLayout) findViewById(R.id.layoutIn2);
        layoutOut1 = (LinearLayout) findViewById(R.id.layoutOut1);
        layoutOut2 = (LinearLayout) findViewById(R.id.layoutOut2);
        layoutOut3 = (LinearLayout) findViewById(R.id.layoutOut3);
        conf = new char[2];

        if(Connect.btSocket != null){
            Connect.ConnectedThread.write(0x40, 2);  //запрос конфигурации
        }

        Connect.mHandler = new Handler() {
            int tmp;
            public void handleMessage(android.os.Message msg) {
                tmp = Connect.buffer[0];
                    if((Connect.buffer[0] & 0xF) != 0){
                        cbIn1.setChecked(true);     //отметить вход 1
                        layoutIn1.setVisibility(View.VISIBLE);
                        switch (tmp & 0x3){                 //вход 1 сторона
                            case 1:
                                rbIn1Left.setChecked(true);
                                break;
                            case 2:
                                rbIn1Right.setChecked(true);
                                break;
                        }
                        switch (tmp & 0xC){                 //вход 1 состояние
                            case 1 << 2:
                                rbIn1On.setChecked(true);
                                break;
                            case 2 << 2:
                                rbIn1Off.setChecked(true);
                                break;
                        }
                    }
                    if((Connect.buffer[0] & 0xF0) != 0){
                        cbIn2.setChecked(true);    //отметить вход 2
                        layoutIn2.setVisibility(View.VISIBLE);
                        switch (tmp & 0x30){                 //вход 2 сторона
                            case 1 << 4:
                                rbIn2Left.setChecked(true);
                                break;
                            case 2 << 4:
                                rbIn2Right.setChecked(true);
                                break;
                        }
                        switch (tmp & 0xC0){                 //вход 2 состояние
                            case 1 << 6:
                                rbIn2On.setChecked(true);
                                break;
                            case 2 << 6:
                                rbIn2Off.setChecked(true);
                                break;
                        }
                    }
                tmp = Connect.buffer[1];
                    if((Connect.buffer[1] & 0x3) != 0){
                        cbOut1.setChecked(true);
                        layoutOut1.setVisibility(View.VISIBLE);
                        switch (tmp & 0x3){
                            case 1:
                                rbOut1Light.setChecked(true);
                                break;
                            case 2:
                                rbOut1Tail.setChecked(true);
                                break;
                        }
                    }
                    if((Connect.buffer[1] & 0xC) != 0) {
                        cbOut2.setChecked(true);
                        layoutOut2.setVisibility(View.VISIBLE);
                        switch (tmp & 0xC) {
                            case 1 << 2:
                                rbOut2Left.setChecked(true);
                                break;
                            case 2 << 2:
                                rbOut2Right.setChecked(true);
                                break;
                        }
                    }
                    if((Connect.buffer[1] & 0x70) != 0){
                        cbOut3.setChecked(true);
                        layoutOut3.setVisibility(View.VISIBLE);
                        switch (tmp & 0x70){
                            case 1 << 4:
                                rbOut3Left.setChecked(true);
                                break;
                            case 2 << 4:
                                rbOut3Right.setChecked(true);
                                break;
                            case 4 << 4:
                                rbOut3Tail.setChecked(true);
                                break;
                        }
                    }

                findViewById(R.id.load).setVisibility(View.GONE);
                findViewById(R.id.base).setVisibility(View.VISIBLE);
            }
        }; //*/
    }

    public void onClick(final View v) {

        switch (v.getId()){
            case R.id.btnClr:               //кнопка очистить конфигурацию
                cbIn1.setChecked(false);
                cbIn2.setChecked(false);
                cbOut1.setChecked(false);
                cbOut2.setChecked(false);
                cbOut3.setChecked(false);
                layoutIn1.setVisibility(v.GONE);
                layoutIn2.setVisibility(v.GONE);
                layoutOut1.setVisibility(v.GONE);
                layoutOut2.setVisibility(v.GONE);
                layoutOut3.setVisibility(v.GONE);
                break;
            case R.id.btnSave:              //кнопка сохранить

                switch (rgIn1Side.getCheckedRadioButtonId()){
                    case R.id.rbIn1Left:

                        break;
                    case R.id.rbIn1Right:

                        break;
                }

                Connect.ConnectedThread.write(0xAD, 0);
                Connect.ConnectedThread.write(0x40, 0);
                Connect.ConnectedThread.write(conf[0], 0);
                Connect.ConnectedThread.write(conf[1], 0);

                //Intent intent_config = new Intent(ConfigKBR.this, Config.class);             //переход к экрану конфигурации
                //startActivity(intent_config);
                this.onBackPressed();
                break;
        }

        if(cbIn1.isChecked()){
            layoutIn1.setVisibility(View.VISIBLE);
        } else {
            layoutIn1.setVisibility(View.GONE);
        }

        if(cbIn2.isChecked()){
            layoutIn2.setVisibility(View.VISIBLE);
        } else {
            layoutIn2.setVisibility(View.GONE);
        }

        if(cbOut1.isChecked()){
            layoutOut1.setVisibility(View.VISIBLE);
        } else {
            layoutOut1.setVisibility(View.GONE);
        }

        if(cbOut2.isChecked()){
            layoutOut2.setVisibility(View.VISIBLE);
        } else {
            layoutOut2.setVisibility(View.GONE);
        }

        if(cbOut3.isChecked()){
            layoutOut3.setVisibility(View.VISIBLE);
        } else {
            layoutOut3.setVisibility(View.GONE);
        }
    }
}
