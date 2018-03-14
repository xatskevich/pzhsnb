package com.xatskevich.pzhsnb;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ConfigPower extends AppCompatActivity {

    public static RadioGroup rgIn1Type, rgIn1Signal, rgIn2Type, rgIn2Signal, rgOut1Type, rgOut2Type, rgOut3Type, rgOut4Type;
    public static RadioButton rbIn1Roll, rbIn1Stair, rbIn2Roll, rbIn2Stair, rbIn1On, rbIn1Off, rbIn2On, rbIn2Off,
                    rbOut1Light, rbOut1Beacon, rbOut1Rear, rbOut1Into,
                    rbOut2Into, rbOut2Beacon, rbOut2Rear, rbOut2Tail,
                    rbOut3Tail, rbOut3Beacon, rbOut3Rear, rbOut3Into,
                    rbOut4Strobe, rbOut4Beacon, rbOut4Rear, rbOut4Into;
    public static CheckBox cbIn1, cbIn2, cbOut1, cbOut2, cbOut3, cbOut4;
    public static LinearLayout layoutIn1, layoutIn2, layoutOut1, layoutOut2, layoutOut3, layoutOut4;
    public static Button btnClr, btnSave;
    private int addr;
    private char power_conf[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_power);

        btnClr = (Button) findViewById(R.id.btnClr);
        btnSave = (Button) findViewById(R.id.btnSave);
        rgIn1Type = (RadioGroup) findViewById(R.id.rgIn1Type);
        rgIn1Signal = (RadioGroup) findViewById(R.id.rgIn1Signal);
        rgIn2Type = (RadioGroup) findViewById(R.id.rgIn2Type);
        rgIn2Signal = (RadioGroup) findViewById(R.id.rgIn2Signal);
        rgOut1Type = (RadioGroup) findViewById(R.id.rgOut1Type);
        rgOut2Type = (RadioGroup) findViewById(R.id.rgOut2Type);
        rgOut3Type = (RadioGroup) findViewById(R.id.rgOut3Type);
        rgOut4Type = (RadioGroup) findViewById(R.id.rgOut4Type);
        rbIn1Roll = (RadioButton) findViewById(R.id.rbIn1Roll);
        rbIn1Stair = (RadioButton) findViewById(R.id.rbIn1Stair);
        rbIn1On = (RadioButton) findViewById(R.id.rbIn1On);
        rbIn1Off = (RadioButton) findViewById(R.id.rbIn1Off);
        rbIn2Roll = (RadioButton) findViewById(R.id.rbIn2Roll);
        rbIn2Stair = (RadioButton) findViewById(R.id.rbIn2Stair);
        rbIn2On = (RadioButton) findViewById(R.id.rbIn2On);
        rbIn2Off = (RadioButton) findViewById(R.id.rbIn2Off);
        rbOut1Beacon = (RadioButton) findViewById(R.id.rbOut1Beacon);
        rbOut1Light = (RadioButton) findViewById(R.id.rbOut1Light);
        rbOut1Rear = (RadioButton) findViewById(R.id.rbOut1Rear);
        rbOut1Into = (RadioButton) findViewById(R.id.rbOut1Into);
        rbOut2Into = (RadioButton) findViewById(R.id.rbOut2Into);
        rbOut2Beacon = (RadioButton) findViewById(R.id.rbOut2Beacon);
        rbOut2Rear = (RadioButton) findViewById(R.id.rbOut2Rear);
        rbOut2Tail = (RadioButton) findViewById(R.id.rbOut2Tail);
        rbOut3Tail = (RadioButton) findViewById(R.id.rbOut3Tail);
        rbOut3Beacon = (RadioButton) findViewById(R.id.rbOut3Beacon);
        rbOut3Rear = (RadioButton) findViewById(R.id.rbOut3Rear);
        rbOut3Into = (RadioButton) findViewById(R.id.rbOut3Into);
        rbOut4Strobe = (RadioButton) findViewById(R.id.rbOut4Strobe);
        rbOut4Beacon = (RadioButton) findViewById(R.id.rbOut4Beacon);
        rbOut4Rear = (RadioButton) findViewById(R.id.rbOut4Rear);
        rbOut4Into = (RadioButton) findViewById(R.id.rbOut4Into);
        cbIn1 = (CheckBox) findViewById(R.id.cbIn1);
        cbIn2 = (CheckBox) findViewById(R.id.cbIn2);
        cbOut1 = (CheckBox) findViewById(R.id.cbOut1);
        cbOut2 = (CheckBox) findViewById(R.id.cbOut2);
        cbOut3 = (CheckBox) findViewById(R.id.cbOut3);
        cbOut4 = (CheckBox) findViewById(R.id.cbOut4);
        layoutIn1 = (LinearLayout) findViewById(R.id.layoutIn1);
        layoutIn2 = (LinearLayout) findViewById(R.id.layoutIn2);
        layoutOut1 = (LinearLayout) findViewById(R.id.layoutOut1);
        layoutOut2 = (LinearLayout) findViewById(R.id.layoutOut2);
        layoutOut3 = (LinearLayout) findViewById(R.id.layoutOut3);
        layoutOut4 = (LinearLayout) findViewById(R.id.layoutOut4);


        Intent intent = getIntent();
        addr = intent.getIntExtra("address", 0);

       // Connect.ConnectedThread.write(0xAB, 1);  //запрос конфигурации
        Connect.ConnectedThread.write(addr, 3);  //запрос конфигурации

    Connect.mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            if(Connect.buffer[0] != addr) {
                Toast.makeText(getBaseContext(), Connect.buffer[0], Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), Connect.buffer[1], Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), "Invalid responce", Toast.LENGTH_SHORT).show();
                finish();
            }
            if ((Connect.buffer[1] & 0xF) != 0) {         //вход1
                cbIn1.setChecked(true);
                layoutIn1.setVisibility(View.VISIBLE);
                switch (Connect.buffer[1] & 0x3) {
                    case 1:
                        rbIn1Roll.setChecked(true);
                        break;
                    case 2:
                        rbIn1Stair.setChecked(true);
                        break;
                }
                switch (Connect.buffer[1] & 0xC) {
                    case 1 << 2:
                        rbIn1On.setChecked(true);
                        break;
                    case 2 << 2:
                        rbIn1Off.setChecked(true);
                        break;
                }
            }
            if ((Connect.buffer[1] & 0xF0) != 0) {            //вход2
                cbIn2.setChecked(true);
                layoutIn2.setVisibility(View.VISIBLE);
                switch (Connect.buffer[1] & 0x30) {
                    case 1 << 4:
                        rbIn2Roll.setChecked(true);
                        break;
                    case 2 << 4:
                        rbIn2Stair.setChecked(true);
                        break;
                }
                switch (Connect.buffer[1] & 0xC0) {
                    case 1 << 6:
                        rbIn2On.setChecked(true);
                        break;
                    case 2 << 6:
                        rbIn2Off.setChecked(true);
                        break;
                }
            }

            cbOut1.setChecked(true);
            layoutOut1.setVisibility(View.VISIBLE);
            switch (Connect.buffer[2] & 0x3) {
                case 0:
                    rbOut1Light.setChecked(true);
                    break;
                case 1:
                    rbOut1Beacon.setChecked(true);
                    break;
                case 2:
                    rbOut1Rear.setChecked(true);
                    break;
                case 3:
                    rbOut1Into.setChecked(true);
                    break;
            }
            cbOut2.setChecked(true);
            layoutOut2.setVisibility(View.VISIBLE);
            switch (Connect.buffer[2] & 0xC) {
                case 0:
                    rbOut2Into.setChecked(true);
                    break;
                case 1<<2:
                    rbOut2Beacon.setChecked(true);
                    break;
                case 2<<2:
                    rbOut2Rear.setChecked(true);
                    break;
                case 3<<2:
                    rbOut2Tail.setChecked(true);
                    break;
            }
            cbOut3.setChecked(true);
            layoutOut3.setVisibility(View.VISIBLE);
            switch (Connect.buffer[2] & 0x30) {
                case 0:
                    rbOut3Tail.setChecked(true);
                    break;
                case 1<<4:
                    rbOut3Beacon.setChecked(true);
                    break;
                case 2<<4:
                    rbOut3Rear.setChecked(true);
                    break;
                case 3<<4:
                    rbOut3Into.setChecked(true);
                    break;
            }
            cbOut4.setChecked(true);
            layoutOut4.setVisibility(View.VISIBLE);
            switch (Connect.buffer[2] & 0xC0) {
                case 0:
                    rbOut4Strobe.setChecked(true);
                    break;
                case 1<<6:
                    rbOut4Beacon.setChecked(true);
                    break;
                case 2<<6:
                    rbOut4Rear.setChecked(true);
                    break;
                case 3<<6:
                    rbOut4Into.setChecked(true);
                    break;
            }

            findViewById(R.id.load).setVisibility(View.GONE);
            findViewById(R.id.base).setVisibility(View.VISIBLE);
        }
    }; //*/

    }

    public void onClick(final View v) {

        power_conf = new char[3];

        switch(v.getId()) {
            case R.id.btnClr:                       //кнопка очистить
                cbIn1.setChecked(false);
                cbIn2.setChecked(false);
                cbOut1.setChecked(false);
                cbOut2.setChecked(false);
                cbOut3.setChecked(false);
                cbOut4.setChecked(false);
                layoutIn1.setVisibility(View.GONE);
                layoutIn2.setVisibility(View.GONE);
                layoutOut1.setVisibility(View.GONE);
                layoutOut2.setVisibility(View.GONE);
                layoutOut3.setVisibility(View.GONE);
                layoutOut4.setVisibility(View.GONE);
                break;
            case R.id.btnSave:                      //кнопка сохранить
                power_conf[0] = (char)addr;
                power_conf[1] = 0;
                power_conf[2] = 0;
                if(cbIn1.isChecked()){      //вход1
                    switch (rgIn1Type.getCheckedRadioButtonId()){
                        case R.id.rbIn1Roll:
                            power_conf[1] |= 1;
                            break;
                        case R.id.rbIn1Stair:
                            power_conf[1] |= 2;
                            break;
                    }
                    switch (rgIn1Signal.getCheckedRadioButtonId()){
                        case R.id.rbIn1On:
                            power_conf[1] |= 1 << 2;
                            break;
                        case R.id.rbIn1Off:
                            power_conf[1] |= 2 << 2;
                            break;
                    }
                }
                if(cbIn2.isChecked()){      //вход2
                    switch (rgIn2Type.getCheckedRadioButtonId()){
                        case R.id.rbIn2Roll:
                            power_conf[1] |= 1 << 4;
                            break;
                        case R.id.rbIn2Stair:
                            power_conf[1] |= 2 << 4;
                            break;
                    }
                    switch (rgIn2Signal.getCheckedRadioButtonId()){
                        case R.id.rbIn2On:
                            power_conf[1] |= 1 << 6;
                            break;
                        case R.id.rbIn2Off:
                            power_conf[1] |= 2 << 6;
                            break;
                    }
                }
                if(cbOut1.isChecked()){
                    switch (rgOut1Type.getCheckedRadioButtonId()){
                        case R.id.rbOut1Light:
                            power_conf[2] |= 0;
                            break;
                        case R.id.rbOut1Beacon:
                            power_conf[2] |= 1;
                            break;
                        case R.id.rbOut1Rear:
                            power_conf[2] |= 2;
                            break;
                        case R.id.rbOut1Into:
                            power_conf[2] |= 3;
                            break;
                    }
                }
                if(cbOut2.isChecked()){
                    switch (rgOut2Type.getCheckedRadioButtonId()){
                        case R.id.rbOut2Into:
                            power_conf[2] |= 0;
                            break;
                        case R.id.rbOut2Beacon:
                            power_conf[2] |= 1 << 2;
                            break;
                        case R.id.rbOut2Rear:
                            power_conf[2] |= 2 << 2;
                            break;
                        case R.id.rbOut2Tail:
                            power_conf[2] |= 3 << 2;
                            break;
                    }
                }
                if(cbOut3.isChecked()){
                    switch (rgOut3Type.getCheckedRadioButtonId()){
                        case R.id.rbOut3Tail:
                            power_conf[2] |= 0;
                            break;
                        case R.id.rbOut3Beacon:
                            power_conf[2] |= 1 << 4;
                            break;
                        case R.id.rbOut3Rear:
                            power_conf[2] |= 2 << 4;
                            break;
                        case R.id.rbOut3Into:
                            power_conf[2] |= 3 << 4;
                            break;
                    }
                }
                if(cbOut4.isChecked()){
                    switch (rgOut4Type.getCheckedRadioButtonId()){
                        case R.id.rbOut4Strobe:
                            power_conf[2] |= 0;
                            break;
                        case R.id.rbOut4Beacon:
                            power_conf[2] |= 1 << 6;
                            break;
                        case R.id.rbOut4Rear:
                            power_conf[2] |= 2 << 6;
                            break;
                        case R.id.rbOut4Into:
                            power_conf[2] |= 3 << 6;
                            break;
                    }
                }

                Connect.ConnectedThread.write(0xAD, 0);
                Connect.ConnectedThread.write(power_conf[0], 0);
                Connect.ConnectedThread.write(power_conf[1], 0);
                Connect.ConnectedThread.write(power_conf[2], 0);
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

        if(cbOut4.isChecked()){
            layoutOut4.setVisibility(View.VISIBLE);
        } else {
            layoutOut4.setVisibility(View.GONE);
        }
    }
}
