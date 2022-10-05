package com.example.tap_tap;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.concurrent.ThreadLocalRandom;


public class EasyModeActivity extends Activity {
    Button tap_one_btn ;
    Button tap_two_btn ;
    Button tap_three_btn ;

    TextView score_field;
    AnimationDrawable animation_one;

    ConstraintLayout first_layout;
    ConstraintLayout second_layout;
    ConstraintLayout third_layout;

    MediaPlayer mp;
    MediaPlayer vibrate_mp;
    int track_duration;
    int[] track_part_array;
    int track_part_number;
    int currentPosition=0; // for media player

    //progress bar
    ProgressBar progressbar;
    int prog=1;

   // for songs bar
    private CountDownTimer timer = null;





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        // buttons
        tap_one_btn=(Button)findViewById(R.id.tap_one_btn);
        tap_two_btn=(Button)findViewById(R.id.tap_two_btn);
        tap_three_btn=(Button)findViewById(R.id.tap_three_btn);
        // score field
        score_field=(TextView)findViewById(R.id.score_field_game);
        // media player
        mp=MediaPlayer.create(EasyModeActivity.this, R.raw.track);
        vibrate_mp=MediaPlayer.create(EasyModeActivity.this, R.raw.vibration);
        track_duration=mp.getDuration();
        track_part_number=(int)(track_duration/800);
        track_part_array=new int[track_part_number];
        for(int i=0;i<track_part_number;i++)
        {
            track_part_array[i] = ThreadLocalRandom.current().nextInt(1,3);
        }


        // layout
        first_layout=(ConstraintLayout) findViewById(R.id.first_layout);
        second_layout=(ConstraintLayout) findViewById(R.id.second_layout);
        third_layout=(ConstraintLayout) findViewById(R.id.third_layout);

        //progress bar
        mp.start();
        progressbar=findViewById(R.id.music_progress_bar);
        progressbar.setMax(track_duration);


        new CountDownTimer(track_duration*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                prog++;
                progressbar.setProgress(prog*track_duration/100);
            }

            public void onFinish() {
                progressbar.setProgress(100);
                mp.stop();
            }
        }.start();


        tap_one_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first_layout.setBackgroundResource(R.drawable.redborder);

                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score++;
                score_field.setText(String.valueOf(present_score));
                currentPosition = mp.getCurrentPosition();
                mp.seekTo(currentPosition +800);
                mp.start();


                new CountDownTimer(800, 800) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        mp.pause();
                        first_layout.setBackgroundResource(R.drawable.border);
                    }
                }.start();

            }
        });

        // animation





        tap_two_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score++;
                score_field.setText(String.valueOf(present_score));
                second_layout.setBackgroundResource(R.drawable.blueborder);
                currentPosition = mp.getCurrentPosition();
                mp.seekTo(currentPosition +800);
                mp.start();

                new CountDownTimer(800, 800) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        mp.pause();
                        second_layout.setBackgroundResource(R.drawable.border);

                    }
                }.start();

            }
        });

        tap_three_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                third_layout.setBackgroundResource(R.drawable.greenborder);
                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score++;
                score_field.setText(String.valueOf(present_score));
                currentPosition = mp.getCurrentPosition();
                mp.seekTo(currentPosition +800);
                mp.start();



                new CountDownTimer(800, 800) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        mp.pause();
                        third_layout.setBackgroundResource(R.drawable.border);
                    }
                }.start();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Handler handler = new Handler();


        int progress=0;
        for(int i=1; i<track_part_number;i++){
            switch (track_part_array[i]){
                case 1:
                    progress+=1100;
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            //Start your animation here
                            addElements(first_layout);
                        }
                    },progress);



                case 2:
                    progress+=1100;
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            //Start your animation here
                            addElements(second_layout);
                        }
                    },progress);


                case 3:
                    progress+=1100;
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            //Start your animation here
                            addElements(third_layout);
                        }
                    },progress);

            }




        }



        //rectangle=addElements(second_layout);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {

        }
    }

    public void addElements(ConstraintLayout layout){

            TextView test=new TextView(this);
            test.setBackgroundResource(R.drawable.note);
            test.setText("hey");
            test.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 140));
            layout.addView(test);
            startAnimation(test, layout);


    }



    public void startAnimation(TextView rectangle, ConstraintLayout layout ){

            Animation animation = new TranslateAnimation(0, 0,0, layout.getHeight() );
            animation.setDuration(1000);
            //animation.setRepeatCount(Animation.INFINITE);
            rectangle.startAnimation(animation);
            rectangle.setVisibility(View.INVISIBLE);


    }

}
