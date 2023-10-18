package com.example.todostectest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView GIF = findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.logoanimada).into(GIF);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                carregar();
            }
        }, 5000);
    }

    public void carregar(){
        startActivity(new Intent(this, TelaInicial.class));
        finish();
    }
}