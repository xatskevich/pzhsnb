package com.xatskevich.pzhsnb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class Config_system extends AppCompatActivity {

    public static RadioGroup rgOverType, rgRpmType, rgIn1Type, rgIn1Side, rgIn2Type, rgIn2Side, rgOut1, rgOut2, rgOut3;
    public static CheckBox cbLightSGU, cbLightSearch, cbLightStrobe, cbLightLeft, cbLightRight, cbLightBeacon, cbLightRear, cbOver, cbRpm, cbPanel, cbIn1, cbIn2, cbOut1, cbOut2, cbOut3;
    public static LinearLayout layoutOver, layoutRpm, layoutPanel, layoutIn1, layoutIn2, layoutOut1, layoutOut2, layoutOut3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_system);

        rgOverType = (RadioGroup) findViewById(R.id.rgOverType);
        rgRpmType = (RadioGroup) findViewById(R.id.rgRpmType);
        rgIn1Type = (RadioGroup) findViewById(R.id.rgIn1Type);
        rgIn1Side = (RadioGroup) findViewById(R.id.rgIn1Side);
        rgIn2Type = (RadioGroup) findViewById(R.id.rgIn2Type);
        rgIn2Side = (RadioGroup) findViewById(R.id.rgIn2Side);
        cbIn1 = (CheckBox) findViewById(R.id.cbIn1);
        cbIn2 = (CheckBox) findViewById(R.id.cbIn2);
        cbOut1 = (CheckBox) findViewById(R.id.cbOut1);
        cbOut2 = (CheckBox) findViewById(R.id.cbOut2);
        cbOut3 = (CheckBox) findViewById(R.id.cbOut3);
        cbOver = (CheckBox) findViewById(R.id.cbOver);
        cbRpm = (CheckBox) findViewById(R.id.cbRpm);
        cbPanel = (CheckBox) findViewById(R.id.cbPanel);
        layoutOver = (LinearLayout) findViewById(R.id.layoutOver);
        layoutRpm = (LinearLayout) findViewById(R.id.layoutRpm);
        layoutPanel = (LinearLayout) findViewById(R.id.layoutPanel);
        layoutIn1 = (LinearLayout) findViewById(R.id.layoutIn1);
        layoutIn2 = (LinearLayout) findViewById(R.id.layoutIn2);
        layoutOut1 = (LinearLayout) findViewById(R.id.layoutOut1);
        layoutOut2 = (LinearLayout) findViewById(R.id.layoutOut2);
        layoutOut3 = (LinearLayout) findViewById(R.id.layoutOut3);

        if(Connect.btSocket != null){
            Connect.ConnectedThread.write(0xAE, 10);  //запрос неадресованных модулей
        }


    }



    public void onClick(final View v) {

        if(cbOver.isChecked()){
            layoutOver.setVisibility(View.VISIBLE);
        } else {
            layoutOver.setVisibility(View.GONE);
        }

        if(cbRpm.isChecked()){
            layoutRpm.setVisibility(View.VISIBLE);
        } else {
            layoutRpm.setVisibility(View.GONE);
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
    }
}
