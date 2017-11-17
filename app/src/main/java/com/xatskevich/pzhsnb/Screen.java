package com.xatskevich.pzhsnb;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Screen extends Activity {

    private Thread mSplashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);

        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    int logoTimer = 0;
                    while (logoTimer < 500) {
                        sleep(100);
                        logoTimer = logoTimer + 100;
                    }
                    startActivity(new Intent(Screen.this, MainActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        logoTimer.start();
    }
}