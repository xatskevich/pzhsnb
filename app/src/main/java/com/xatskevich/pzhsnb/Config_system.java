package com.xatskevich.pzhsnb;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Config_system extends AppCompatActivity {

    public static RadioGroup rgRpmType, rgIn1Type, rgIn1Signal, rgIn2Type, rgIn2Signal, rgKBRSignal,
            rgOut1Type, rgOut2Type, rgOut3Type, rgOut4Type, rgOut5Type;
    public static RadioButton rbRpm07, rbRpm24, rbKBRActive, rbKBRInactive, rbIn1Roll, rbIn1Stair, rbIn2Roll,
            rbIn2Stair, rbIn1On, rbIn1Off, rbIn2On, rbIn2Off, rbOut1Left, rbOut1Right, rbOut1Beacon, rbOut1Rear,
            rbOut1Into, rbOut1Strobe, rbOut1Tail, rbOut2Left, rbOut2Right, rbOut2Beacon, rbOut2Rear,
            rbOut2Into, rbOut2Strobe, rbOut2Tail, rbOut3Left, rbOut3Right, rbOut3Beacon, rbOut3Rear,
            rbOut3Into, rbOut3Strobe, rbOut3Tail, rbOut4Left, rbOut4Right, rbOut4Beacon, rbOut4Rear,
            rbOut4Into, rbOut4Strobe, rbOut4Tail, rbOut5Left, rbOut5Right, rbOut5Beacon, rbOut5Rear,
            rbOut5Into, rbOut5Strobe, rbOut5Tail;
    public static CheckBox cbLightSGU, cbLightSearch, cbLightStrobe, cbLightLeft, cbLightRight, cbLightInto,
            cbLightBeacon, cbLightRear, cbRpm, cbPanel, cbIn1, cbIn2, cbOut1, cbOut2, cbOut3, cbOut4, cbOut5,
            cbKBR;
    public static LinearLayout layoutRpm, layoutPanel, layoutIn1, layoutIn2, layoutKBR,
            layoutOut1, layoutOut2, layoutOut3, layoutOut4, layoutOut5;
    private char sys_conf[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_system);

        rgRpmType = (RadioGroup) findViewById(R.id.rgRpmType);
        rgIn1Type = (RadioGroup) findViewById(R.id.rgIn1Type);
        rgIn1Signal = (RadioGroup) findViewById(R.id.rgIn1Signal);
        rgIn2Type = (RadioGroup) findViewById(R.id.rgIn2Type);
        rgIn2Signal = (RadioGroup) findViewById(R.id.rgIn2Signal);
        rgRpmType = (RadioGroup) findViewById(R.id.rgRpmType);
        rgKBRSignal = (RadioGroup) findViewById(R.id.rgKBRSignal);
        rgOut1Type = (RadioGroup) findViewById(R.id.rgOut1Type);
        rgOut2Type = (RadioGroup) findViewById(R.id.rgOut2Type);
        rgOut3Type = (RadioGroup) findViewById(R.id.rgOut3Type);
        rgOut4Type = (RadioGroup) findViewById(R.id.rgOut4Type);
        rgOut5Type = (RadioGroup) findViewById(R.id.rgOut5Type);
        rbRpm07 = (RadioButton) findViewById(R.id.rbRpm07);
        rbRpm24 = (RadioButton) findViewById(R.id.rbRpm24);
        rbKBRActive = (RadioButton) findViewById(R.id.rbKBRActive);
        rbKBRInactive = (RadioButton) findViewById(R.id.rbKBRInactive);
        rbIn1Roll = (RadioButton) findViewById(R.id.rbIn1Roll);
        rbIn2Roll = (RadioButton) findViewById(R.id.rbIn2Roll);
        rbIn1Stair = (RadioButton) findViewById(R.id.rbIn1Stair);
        rbIn2Stair = (RadioButton) findViewById(R.id.rbIn2Stair);
        rbIn1On = (RadioButton) findViewById(R.id.rbIn1On);
        rbIn2On = (RadioButton) findViewById(R.id.rbIn2On);
        rbIn1Off = (RadioButton) findViewById(R.id.rbIn1Off);
        rbIn2Off = (RadioButton) findViewById(R.id.rbIn2Off);
        rbOut1Left = (RadioButton) findViewById(R.id.rbOut1Left);
        rbOut1Right = (RadioButton) findViewById(R.id.rbOut1Right);
        rbOut1Beacon = (RadioButton) findViewById(R.id.rbOut1Beacon);
        rbOut1Rear = (RadioButton) findViewById(R.id.rbOut1Rear);
        rbOut1Into = (RadioButton) findViewById(R.id.rbOut1Into);
        rbOut1Strobe = (RadioButton) findViewById(R.id.rbOut1Strobe);
        rbOut1Tail = (RadioButton) findViewById(R.id.rbOut1Tail);
        rbOut2Left = (RadioButton) findViewById(R.id.rbOut2Left);
        rbOut2Right = (RadioButton) findViewById(R.id.rbOut2Right);
        rbOut2Beacon = (RadioButton) findViewById(R.id.rbOut2Beacon);
        rbOut2Rear = (RadioButton) findViewById(R.id.rbOut2Rear);
        rbOut2Into = (RadioButton) findViewById(R.id.rbOut2Into);
        rbOut2Strobe = (RadioButton) findViewById(R.id.rbOut2Strobe);
        rbOut2Tail = (RadioButton) findViewById(R.id.rbOut2Tail);
        rbOut3Left = (RadioButton) findViewById(R.id.rbOut3Left);
        rbOut3Right = (RadioButton) findViewById(R.id.rbOut3Right);
        rbOut3Beacon = (RadioButton) findViewById(R.id.rbOut3Beacon);
        rbOut3Rear = (RadioButton) findViewById(R.id.rbOut3Rear);
        rbOut3Into = (RadioButton) findViewById(R.id.rbOut3Into);
        rbOut3Strobe = (RadioButton) findViewById(R.id.rbOut3Strobe);
        rbOut3Tail = (RadioButton) findViewById(R.id.rbOut3Tail);
        rbOut4Left = (RadioButton) findViewById(R.id.rbOut4Left);
        rbOut4Right = (RadioButton) findViewById(R.id.rbOut4Right);
        rbOut4Beacon = (RadioButton) findViewById(R.id.rbOut4Beacon);
        rbOut4Rear = (RadioButton) findViewById(R.id.rbOut4Rear);
        rbOut4Into = (RadioButton) findViewById(R.id.rbOut4Into);
        rbOut4Strobe = (RadioButton) findViewById(R.id.rbOut4Strobe);
        rbOut4Tail = (RadioButton) findViewById(R.id.rbOut4Tail);
        rbOut5Left = (RadioButton) findViewById(R.id.rbOut5Left);
        rbOut5Right = (RadioButton) findViewById(R.id.rbOut5Right);
        rbOut5Beacon = (RadioButton) findViewById(R.id.rbOut5Beacon);
        rbOut5Rear = (RadioButton) findViewById(R.id.rbOut5Rear);
        rbOut5Into = (RadioButton) findViewById(R.id.rbOut5Into);
        rbOut5Strobe = (RadioButton) findViewById(R.id.rbOut5Strobe);
        rbOut5Tail = (RadioButton) findViewById(R.id.rbOut5Tail);
        cbLightSGU = (CheckBox) findViewById(R.id.cbLightSGU);
        cbLightBeacon = (CheckBox) findViewById(R.id.cbLightBeacon);
        cbLightInto = (CheckBox) findViewById(R.id.cbLightInto);
        cbLightLeft = (CheckBox) findViewById(R.id.cbLightLeft);
        cbLightRight = (CheckBox) findViewById(R.id.cbLightRight);
        cbLightRear = (CheckBox) findViewById(R.id.cbLightRear);
        cbLightSearch = (CheckBox) findViewById(R.id.cbLightSearch);
        cbLightStrobe = (CheckBox) findViewById(R.id.cbLightStrobe);
        cbIn1 = (CheckBox) findViewById(R.id.cbIn1);
        cbIn2 = (CheckBox) findViewById(R.id.cbIn2);
        cbOut1 = (CheckBox) findViewById(R.id.cbOut1);
        cbOut2 = (CheckBox) findViewById(R.id.cbOut2);
        cbOut3 = (CheckBox) findViewById(R.id.cbOut3);
        cbOut4 = (CheckBox) findViewById(R.id.cbOut4);
        cbOut5 = (CheckBox) findViewById(R.id.cbOut5);
        cbRpm = (CheckBox) findViewById(R.id.cbRpm);
        cbKBR = (CheckBox) findViewById(R.id.cbKBR);
        cbPanel = (CheckBox) findViewById(R.id.cbPanel);
        layoutRpm = (LinearLayout) findViewById(R.id.layoutRpm);
        layoutPanel = (LinearLayout) findViewById(R.id.layoutPanel);
        layoutIn1 = (LinearLayout) findViewById(R.id.layoutIn1);
        layoutIn2 = (LinearLayout) findViewById(R.id.layoutIn2);
        layoutOut1 = (LinearLayout) findViewById(R.id.layoutOut1);
        layoutOut2 = (LinearLayout) findViewById(R.id.layoutOut2);
        layoutOut3 = (LinearLayout) findViewById(R.id.layoutOut3);
        layoutOut4 = (LinearLayout) findViewById(R.id.layoutOut4);
        layoutOut5 = (LinearLayout) findViewById(R.id.layoutOut5);
        layoutKBR = (LinearLayout) findViewById(R.id.layoutKBR);

    }

    @Override
    public void onResume() {
        super.onResume();

        findViewById(R.id.load).setVisibility(View.VISIBLE);
        findViewById(R.id.base).setVisibility(View.GONE);

        if(Connect.btSocket != null){
            Connect.ConnectedThread.write(0xAA, 6);  //запрос неадресованных модулей
        }///*/

        Connect.mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                findViewById(R.id.load).setVisibility(View.GONE);
                findViewById(R.id.base).setVisibility(View.VISIBLE);

                if((Connect.buffer[0] & 1) != 0) cbLightSGU.setChecked(true);       //СГУ
                else cbLightSGU.setChecked(false);
                if((Connect.buffer[0] & 2) != 0) cbLightLeft.setChecked(true);      //освещение слева
                else cbLightLeft.setChecked(false);
                if((Connect.buffer[0] & 4) != 0) cbLightBeacon.setChecked(true);    //маяк
                else cbLightBeacon.setChecked(false);
                if((Connect.buffer[0] & 8) != 0) cbLightInto.setChecked(true);      //освещение отсеков
                else cbLightInto.setChecked(false);
                if((Connect.buffer[0] & 0x10) != 0) cbLightRear.setChecked(true);   //задний прожектор
                else cbLightRear.setChecked(false);
                if((Connect.buffer[0] & 0x20) != 0) cbLightRight.setChecked(true);  //освещение справа
                else cbLightRight.setChecked(false);
                if((Connect.buffer[0] & 0x40) != 0) cbLightStrobe.setChecked(true); //строб
                else cbLightStrobe.setChecked(false);
                if((Connect.buffer[0] & 0x80) != 0) cbLightSearch.setChecked(true); //фара-искатель
                else cbLightSearch.setChecked(false);

                if(Connect.buffer[4] == 0x07) rbRpm07.setChecked(true);     //адрес управления оборотами
                if(Connect.buffer[4] == 0x24) rbRpm24.setChecked(true);
                if(Connect.buffer[4] != 0){
                    cbRpm.setChecked(true);
                    layoutRpm.setVisibility(View.VISIBLE);
                }

                cbKBR.setChecked(true);
                layoutKBR.setVisibility(View.VISIBLE);
                if((Connect.buffer[3] & 0x80) != 0) rbKBRInactive.setChecked(true); //двери КБР сигнал
                else rbKBRActive.setChecked(true);

                if(Connect.buffer[1] != 0){             //входа пульта
                    cbPanel.setChecked(true);
                    layoutPanel.setVisibility(View.VISIBLE);
                }

                if((Connect.buffer[1] & 0x0F) != 0){    //открыть вход 1
                    cbIn1.setChecked(true);
                    layoutIn1.setVisibility(View.VISIBLE);
                    if((Connect.buffer[1] & 1) != 0) rbIn1Roll.setChecked(true);
                    if((Connect.buffer[1] & 2) != 0) rbIn1Stair.setChecked(true);
                    if((Connect.buffer[1] & 4) != 0) rbIn1On.setChecked(true);
                    if((Connect.buffer[1] & 8) != 0) rbIn1Off.setChecked(true);
                }

                if((Connect.buffer[1] & 0xF0) != 0){    //открыть вход 2
                    cbIn2.setChecked(true);
                    layoutIn2.setVisibility(View.VISIBLE);
                    if((Connect.buffer[1] & 0x10) != 0) rbIn2Roll.setChecked(true);
                    if((Connect.buffer[1] & 0x20) != 0) rbIn2Stair.setChecked(true);
                    if((Connect.buffer[1] & 0x40) != 0) rbIn2On.setChecked(true);
                    if((Connect.buffer[1] & 0x80) != 0) rbIn2Off.setChecked(true);
                }

                if((Connect.buffer[2] & 0x0F) != 0) {       //выход 1
                    cbOut1.setChecked(true);
                    layoutOut1.setVisibility(View.VISIBLE);
                    switch (Connect.buffer[2] & 0x0F) {
                        case 1:
                            rbOut1Left.setChecked(true);
                            break;
                        case 2:
                            rbOut1Right.setChecked(true);
                            break;
                        case 3:
                            rbOut1Beacon.setChecked(true);
                            break;
                        case 4:
                            rbOut1Rear.setChecked(true);
                            break;
                        case 5:
                            rbOut1Into.setChecked(true);
                            break;
                        case 6:
                            rbOut1Strobe.setChecked(true);
                            break;
                        case 7:
                            rbOut1Tail.setChecked(true);
                            break;
                    }
                }

                if((Connect.buffer[2] & 0xF0) != 0) {       //выход 2
                    cbOut2.setChecked(true);
                    layoutOut2.setVisibility(View.VISIBLE);
                    switch (Connect.buffer[2] & 0xF0) {
                        case 0x10:
                            rbOut2Left.setChecked(true);
                            break;
                        case 0x20:
                            rbOut2Right.setChecked(true);
                            break;
                        case 0x30:
                            rbOut2Beacon.setChecked(true);
                            break;
                        case 0x40:
                            rbOut2Rear.setChecked(true);
                            break;
                        case 0x50:
                            rbOut2Into.setChecked(true);
                            break;
                        case 0x60:
                            rbOut2Strobe.setChecked(true);
                            break;
                        case 0x70:
                            rbOut2Tail.setChecked(true);
                            break;
                    }
                }

                if((Connect.buffer[3] & 0x0F) != 0) {       //выход 3
                    cbOut3.setChecked(true);
                    layoutOut3.setVisibility(View.VISIBLE);
                    switch (Connect.buffer[3] & 0x0F) {
                        case 1:
                            rbOut3Left.setChecked(true);
                            break;
                        case 2:
                            rbOut3Right.setChecked(true);
                            break;
                        case 3:
                            rbOut3Beacon.setChecked(true);
                            break;
                        case 4:
                            rbOut3Rear.setChecked(true);
                            break;
                        case 5:
                            rbOut3Into.setChecked(true);
                            break;
                        case 6:
                            rbOut3Strobe.setChecked(true);
                            break;
                        case 7:
                            rbOut3Tail.setChecked(true);
                            break;
                    }
                }

                if((Connect.buffer[5] & 0x0F) != 0) {       //выход 4
                    cbOut4.setChecked(true);
                    layoutOut4.setVisibility(View.VISIBLE);
                    switch (Connect.buffer[5] & 0x0F) {
                        case 1:
                            rbOut4Left.setChecked(true);
                            break;
                        case 2:
                            rbOut4Right.setChecked(true);
                            break;
                        case 3:
                            rbOut4Beacon.setChecked(true);
                            break;
                        case 4:
                            rbOut4Rear.setChecked(true);
                            break;
                        case 5:
                            rbOut4Into.setChecked(true);
                            break;
                        case 6:
                            rbOut4Strobe.setChecked(true);
                            break;
                        case 7:
                            rbOut4Tail.setChecked(true);
                            break;
                    }
                }

                if((Connect.buffer[5] & 0xF0) != 0) {       //выход 5
                    cbOut5.setChecked(true);
                    layoutOut5.setVisibility(View.VISIBLE);
                    switch (Connect.buffer[5] & 0xF0) {
                        case 0x10:
                            rbOut5Left.setChecked(true);
                            break;
                        case 0x20:
                            rbOut5Right.setChecked(true);
                            break;
                        case 0x30:
                            rbOut5Beacon.setChecked(true);
                            break;
                        case 0x40:
                            rbOut5Rear.setChecked(true);
                            break;
                        case 0x50:
                            rbOut5Into.setChecked(true);
                            break;
                        case 0x60:
                            rbOut5Strobe.setChecked(true);
                            break;
                        case 0x70:
                            rbOut5Tail.setChecked(true);
                            break;
                    }
                }

            }
        };
    }


    public void onClick(final View v) {

        sys_conf = new char[6];

        switch(v.getId()){
            case R.id.btnClr:                       //кнопка очистить
                cbLightSGU.setChecked(false);
                cbLightSearch.setChecked(false);
                cbLightStrobe.setChecked(false);
                cbLightLeft.setChecked(false);
                cbLightRight.setChecked(false);
                cbLightInto.setChecked(false);
                cbLightBeacon.setChecked(false);
                cbLightRear.setChecked(false);
                cbRpm.setChecked(false);
                cbKBR.setChecked(false);
                cbPanel.setChecked(false);
                cbIn1.setChecked(false);
                cbIn2.setChecked(false);
                cbOut1.setChecked(false);
                cbOut2.setChecked(false);
                cbOut3.setChecked(false);
                cbOut4.setChecked(false);
                cbOut5.setChecked(false);
                layoutIn1.setVisibility(View.GONE);
                layoutIn2.setVisibility(View.GONE);
                layoutOut1.setVisibility(View.GONE);
                layoutOut2.setVisibility(View.GONE);
                layoutOut3.setVisibility(View.GONE);
                layoutOut4.setVisibility(View.GONE);
                layoutOut5.setVisibility(View.GONE);
                layoutPanel.setVisibility(View.GONE);
                layoutKBR.setVisibility(View.GONE);
                layoutRpm.setVisibility(View.GONE);
                break;
            case R.id.btnSave:                      //кнопка сохранить
                sys_conf[0] = 0;
                sys_conf[1] = 0;
                sys_conf[2] = 0;
                sys_conf[3] = 0;
                sys_conf[4] = 0;
                sys_conf[5] = 0;
                if(cbLightSGU.isChecked()) sys_conf[0] |= 1;    //конфиг светотехники
                if(cbLightLeft.isChecked()) sys_conf[0] |= 2;
                if(cbLightBeacon.isChecked()) sys_conf[0] |= 4;
                if(cbLightInto.isChecked()) sys_conf[0] |= 8;
                if(cbLightRear.isChecked()) sys_conf[0] |= 0x10;
                if(cbLightRight.isChecked()) sys_conf[0] |= 0x20;
                if(cbLightStrobe.isChecked()) sys_conf[0] |= 0x40;
                if(cbLightSearch.isChecked()) sys_conf[0] |= 0x80;

                if(cbKBR.isChecked()){
                    if(rgKBRSignal.getCheckedRadioButtonId() == R.id.rbKBRInactive) sys_conf[3] |= 0x80;  //сигнал открытых дверей
                }

                if(cbRpm.isChecked()){
                    switch (rgRpmType.getCheckedRadioButtonId()){         //выбор адреса управления оборотами
                        case R.id.rbRpm07:
                            sys_conf[4] = 0x07;
                            break;
                        case R.id.rbRpm24:
                            sys_conf[4] = 0x24;
                            break;
                    }
                }

                if(cbPanel.isChecked()){
                    if(cbIn1.isChecked()){
                        switch (rgIn1Type.getCheckedRadioButtonId()){
                            case R.id.rbIn1Roll:
                                sys_conf[1] |= 1;
                                break;
                            case R.id.rbIn1Stair:
                                sys_conf[1] |= 2;
                                break;
                        }
                        switch (rgIn1Signal.getCheckedRadioButtonId()){
                            case R.id.rbIn1On:
                                sys_conf[1] |= 4;
                                break;
                            case R.id.rbIn1Off:
                                sys_conf[1] |= 8;
                                break;
                        }
                    }
                    if(cbIn2.isChecked()){
                        switch (rgIn2Type.getCheckedRadioButtonId()){
                            case R.id.rbIn2Roll:
                                sys_conf[1] |= 0x10;
                                break;
                            case R.id.rbIn2Stair:
                                sys_conf[1] |= 0x20;
                                break;
                        }
                        switch (rgIn2Signal.getCheckedRadioButtonId()){
                            case R.id.rbIn2On:
                                sys_conf[1] |= 0x40;
                                break;
                            case R.id.rbIn2Off:
                                sys_conf[1] |= 0x80;
                                break;
                        }
                    }
                    if(cbOut1.isChecked()){
                        switch (rgOut1Type.getCheckedRadioButtonId()){
                            case R.id.rbOut1Left:
                                sys_conf[2] |= 1;
                                break;
                            case R.id.rbOut1Right:
                                sys_conf[2] |= 2;
                                break;
                            case R.id.rbOut1Beacon:
                                sys_conf[2] |= 3;
                                break;
                            case R.id.rbOut1Rear:
                                sys_conf[2] |= 4;
                                break;
                            case R.id.rbOut1Into:
                                sys_conf[2] |= 5;
                                break;
                            case R.id.rbOut1Strobe:
                                sys_conf[2] |= 6;
                                break;
                            case R.id.rbOut1Tail:
                                sys_conf[2] |= 7;
                                break;
                        }
                    }
                    if(cbOut2.isChecked()){
                        switch (rgOut2Type.getCheckedRadioButtonId()){
                            case R.id.rbOut2Left:
                                sys_conf[2] |= (1<<4);
                                break;
                            case R.id.rbOut2Right:
                                sys_conf[2] |= (2<<4);
                                break;
                            case R.id.rbOut2Beacon:
                                sys_conf[2] |= (3<<4);
                                break;
                            case R.id.rbOut2Rear:
                                sys_conf[2] |= (4<<4);
                                break;
                            case R.id.rbOut2Into:
                                sys_conf[2] |= (5<<4);
                                break;
                            case R.id.rbOut2Strobe:
                                sys_conf[2] |= (6<<4);
                                break;
                            case R.id.rbOut2Tail:
                                sys_conf[2] |= (7<<4);
                                break;
                        }
                    }
                    if(cbOut3.isChecked()){
                        switch (rgOut3Type.getCheckedRadioButtonId()){
                            case R.id.rbOut3Left:
                                sys_conf[3] |= 1;
                                break;
                            case R.id.rbOut3Right:
                                sys_conf[3] |= 2;
                                break;
                            case R.id.rbOut3Beacon:
                                sys_conf[3] |= 3;
                                break;
                            case R.id.rbOut3Rear:
                                sys_conf[3] |= 4;
                                break;
                            case R.id.rbOut3Into:
                                sys_conf[3] |= 5;
                                break;
                            case R.id.rbOut3Strobe:
                                sys_conf[3] |= 6;
                                break;
                            case R.id.rbOut3Tail:
                                sys_conf[3] |= 7;
                                break;
                        }
                    }
                    if(cbOut4.isChecked()){
                        switch (rgOut4Type.getCheckedRadioButtonId()){
                            case R.id.rbOut4Left:
                                sys_conf[5] |= 1;
                                break;
                            case R.id.rbOut4Right:
                                sys_conf[5] |= 2;
                                break;
                            case R.id.rbOut4Beacon:
                                sys_conf[5] |= 3;
                                break;
                            case R.id.rbOut4Rear:
                                sys_conf[5] |= 4;
                                break;
                            case R.id.rbOut4Into:
                                sys_conf[5] |= 5;
                                break;
                            case R.id.rbOut4Strobe:
                                sys_conf[5] |= 6;
                                break;
                            case R.id.rbOut4Tail:
                                sys_conf[5] |= 7;
                                break;
                        }
                    }
                    if(cbOut5.isChecked()){
                        switch (rgOut5Type.getCheckedRadioButtonId()){
                            case R.id.rbOut5Left:
                                sys_conf[5] |= (1<<4);
                                break;
                            case R.id.rbOut5Right:
                                sys_conf[5] |= (2<<4);
                                break;
                            case R.id.rbOut5Beacon:
                                sys_conf[5] |= (3<<4);
                                break;
                            case R.id.rbOut5Rear:
                                sys_conf[5] |= (4<<4);
                                break;
                            case R.id.rbOut5Into:
                                sys_conf[5] |= (5<<4);
                                break;
                            case R.id.rbOut5Strobe:
                                sys_conf[5] |= (6<<4);
                                break;
                            case R.id.rbOut5Tail:
                                sys_conf[5] |= (7<<4);
                                break;
                        }
                    }
                }

                Connect.ConnectedThread.write(0xAC, 0);     //отправка конфига
                Connect.ConnectedThread.write(sys_conf[0], 0);
                Connect.ConnectedThread.write(sys_conf[1], 0);
                Connect.ConnectedThread.write(sys_conf[2], 0);
                Connect.ConnectedThread.write(sys_conf[3], 0);
                Connect.ConnectedThread.write(sys_conf[4], 0);
                Connect.ConnectedThread.write(sys_conf[5], 0);
                this.onBackPressed();
                break;
        }

        if(cbRpm.isChecked()){
            layoutRpm.setVisibility(View.VISIBLE);
        } else {
            layoutRpm.setVisibility(View.GONE);
        }

        if(cbKBR.isChecked()){
            layoutKBR.setVisibility(View.VISIBLE);
        } else {
            layoutKBR.setVisibility(View.GONE);
        }

        if(cbPanel.isChecked()){
            layoutPanel.setVisibility(View.VISIBLE);
        } else {
            layoutPanel.setVisibility(View.GONE);
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

        if(cbOut5.isChecked()){
            layoutOut5.setVisibility(View.VISIBLE);
        } else {
            layoutOut5.setVisibility(View.GONE);
        }
    }
}
