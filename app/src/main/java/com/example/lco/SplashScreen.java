package com.example.lco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SplashScreen extends AppCompatActivity {
 ImageView splash_img;
 ArrayList<ExerciseDTO> exerciseList;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        exerciseList=new ExerciseDataProvider().getData();
        splash_img= findViewById(R.id.splash_logo_img);
        Animation animation= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.fadein);
       splash_img.startAnimation(animation);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent=new Intent(SplashScreen.this,MainActivity.class);
               exerciseList=getRandomArrayList(5,exerciseList.size(),exerciseList);

               intent.putParcelableArrayListExtra("excercise_list",  exerciseList);
              startActivity(intent);
           }
       }, 2000);

    }

    private ArrayList<ExerciseDTO> getRandomArrayList(int numberOfRandom, int size, ArrayList<ExerciseDTO> exerciseList) {
        ArrayList<ExerciseDTO> randomList=new ArrayList<>();
        Random random=new Random();

        HashSet<Integer> hashSet=new HashSet<>();
        while(hashSet.size()!=numberOfRandom)
        {
            int randomNumber=random.nextInt(size);
            if(!hashSet.contains(randomNumber))
            {
                hashSet.add(randomNumber);
            }
        }
        Iterator<Integer> iterator=hashSet.iterator();
        while (iterator.hasNext())
        {

            randomList.add(exerciseList.get(iterator.next()));
        }


        return randomList;
    }
}
