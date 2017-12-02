package com.example.user.epharma_demo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    TextView tv;
    Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv = (TextView) findViewById(R.id.textView);
        tf = Typeface.createFromAsset(getAssets(), "fonts/font1.ttf");
        tv.setTypeface(tf);
        getSupportActionBar().hide();
        Thread mythread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4200);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    SplashScreen.this.overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        mythread.start();
    }
}
