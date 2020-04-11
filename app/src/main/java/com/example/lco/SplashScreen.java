package com.example.lco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
 ImageView splash_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splash_img= findViewById(R.id.splash_logo_img);
        Animation animation= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.fadein);
       splash_img.startAnimation(animation);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent=new Intent(SplashScreen.this,MainActivity.class);
               startActivity(intent);
           }
       }, 2000);

    }
}
