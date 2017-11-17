package com.xatskevich.pzhsnb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class ConfigPower extends AppCompatActivity {

    public static RadioGroup rgIn1Type, rgIn1Side, rgIn2Type, rgIn2Side;
    public static CheckBox cbIn1, cbIn2, cbOut1, cbOut2, cbOut3;
    public static LinearLayout layoutIn1, layoutIn2, layoutOut1, layoutOut2, layoutOut3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_power);

    rgIn1Type = (RadioGroup) findViewById(R.id.rgIn1Type);
    rgIn1Side = (RadioGroup) findViewById(R.id.rgIn1Side);
    rgIn2Type = (RadioGroup) findViewById(R.id.rgIn2Type);
    rgIn2Side = (RadioGroup) findViewById(R.id.rgIn2Side);
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
}

    public void onClick(final View v) {

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
