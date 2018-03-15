package com.example.quoctuan.lazadaapp.view.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.quoctuan.lazadaapp.R;
import com.example.quoctuan.lazadaapp.view.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (Exception e) {

                }finally {
                    Intent home = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(home);
                }
            }
        });
        thread.start();
    }
}
