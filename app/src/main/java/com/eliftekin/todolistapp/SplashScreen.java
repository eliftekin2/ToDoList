package com.eliftekin.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    Handler handler = new Handler(); //splash screen için handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //navigasyon barının rengini değiştirir
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.maingrey));
        //tam ekran yapar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        handler.postDelayed(new Runnable() { //belli bir gecikme süresiyle runnable nesnesi planlar
            @Override
            public void run() { //gecikme süresi sonrasında yürütülecek işlemler
                Intent intent = new Intent(SplashScreen.this, MainActivity.class); //aktiviteler arası geçiş
                startActivity(intent);
                finish();
            }
        },3000); //gecikme süresi
    }
}