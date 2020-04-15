package com.example.lco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ArrayList<ExerciseDTO> exerciseDTOList;
    ExerciseAdapter exerciseAdapter;
    private static final String TAG = "MainActivity";
    Button pause_music;
    TextView timer_text;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        exerciseDTOList = intent.getParcelableArrayListExtra("excercise_list");
        Log.d(TAG, String.valueOf(exerciseDTOList.size()));
        RecyclerView recyclerView = findViewById(R.id.recycler_view_id);
        exerciseAdapter = new ExerciseAdapter(exerciseDTOList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(exerciseAdapter);
        pause_music=findViewById(R.id.pause_music);
        timer_text=findViewById(R.id.timer_text);
        findViewById(R.id.start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startExcercise(exerciseDTOList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        pause_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMusic();
            }
        });


    }

    private void startExcercise(ArrayList<ExerciseDTO> exerciseDTOList) throws IOException {
        ArrayList<Integer> time_of_excercise_list=new ArrayList<>();
        for(ExerciseDTO exerciseDTO:exerciseDTOList)
        {
            int time=0;
            String time_string=exerciseDTO.getExercise_time();
            Log.d(TAG,exerciseDTO.getExercise_name());
            if(time_string.contains("sec"))
            {
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(time_string);
                while(m.find()) {

                    time=Integer.parseInt(m.group());
                    time_of_excercise_list.add(time*1000);
                }
            }
            else if (time_string.contains("min"))
            {
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(time_string);
                while(m.find()) {

                    time=Integer.parseInt(m.group());
                    time_of_excercise_list.add(time*1000*60);
                }
            }


        }
        Log.d(TAG,time_of_excercise_list.toString());

        for (Integer timeofGym:time_of_excercise_list)
        {
            Log.d(TAG,"inside for Loop");


            callTimerMethod(timeofGym);
        }


    }


    private void callTimerMethod(Integer timeofGym) throws IOException {
        Log.d(TAG,"callTimerMethod");

        startMusic();
        startTimer((long)timeofGym,1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PauseTimerMethod();
            }
        },timeofGym);
    }

    private void PauseTimerMethod() {
        Log.d(TAG,"PauseTimerMethod");
        pauseMusic();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    startMusic();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        },10000);
    }

    private void startMusic() throws IOException {
        Log.d(TAG,"music Start");
      if (player == null) {
          player=new MediaPlayer();
          player.setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
          player.prepare();
          player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
              @Override
              public void onCompletion(MediaPlayer mp) {
                  Log.d(TAG,"songz Finished");
                  stopMusic();
              }
          });
      }

        player.start();
    }
    private void pauseMusic() {
        if (player != null)
            player.pause();


    }
    private void stopMusic() {
       stopPlayer();
    }
    private void stopPlayer() {
        if (player != null)
        {
            player.release();
            player=null;
            Toast.makeText(this,"player Stop",Toast.LENGTH_SHORT).show();
        }



    }
    public void startTimer(final long finish, long tick) {
        Log.d(TAG,"startTimer");
        CountDownTimer t;
        t = new CountDownTimer(finish, tick) {

            public void onTick(long millisUntilFinished) {
                long remainedSecs = millisUntilFinished / 1000;
                timer_text.setText("" + (remainedSecs / 60) + ":" + (remainedSecs % 60));// manage it accordign to you
            }

            public void onFinish() {
                timer_text.setText("00:00:00");
                Toast.makeText(MainActivity.this, "Finish", Toast.LENGTH_SHORT).show();

                cancel();
            }
        };
        t.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
        stopPlayer();
    }
}
