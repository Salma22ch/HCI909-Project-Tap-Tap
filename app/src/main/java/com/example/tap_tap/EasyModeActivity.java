package com.example.tap_tap;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

    // progress bar + timer to update the prgress bar
    int current_ps=0;
    //progress bar
    ProgressBar progressbar;
    TextView timer_pg_bar;
    int prog=1;
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
        track_part_number=(int)(track_duration/1000);
        track_part_array=new int[track_part_number];

        for(int i=0;i<track_part_number;i++)
        {
            track_part_array[i] = ThreadLocalRandom.current().nextInt(1,4);
        }


        // layout
        first_layout=(ConstraintLayout) findViewById(R.id.first_layout);
        second_layout=(ConstraintLayout) findViewById(R.id.second_layout);
        third_layout=(ConstraintLayout) findViewById(R.id.third_layout);


        //progress bar
        mp.start();
        timer_pg_bar=findViewById(R.id.timer);
        progressbar=findViewById(R.id.music_progress_bar);
        progressbar.setMax(track_duration);


        new CountDownTimer(track_duration*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                prog++;
                progressbar.setProgress(prog*track_duration/100);
                timer_pg_bar.setText(String.valueOf(prog));
            }

            public void onFinish() {
                progressbar.setProgress(100);
                mp.stop();
                first_layout.removeViews(1, first_layout.getChildCount() - 1);
                second_layout.removeViews(1, second_layout.getChildCount() - 1);
                third_layout.removeViews(1, third_layout.getChildCount() - 1);
            }
        }.start();


        tap_one_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first_layout.setBackgroundResource(R.drawable.redborder);
                if(track_part_array[prog+1]==1) {
                    int present_score = Integer.parseInt(score_field.getText().toString());
                    present_score++;
                    score_field.setText(String.valueOf(present_score));
                }else{
                    Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
                }

                new CountDownTimer(800, 800) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        first_layout.setBackgroundResource(R.drawable.border);
                    }
                }.start();

            }
        });



        tap_two_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                second_layout.setBackgroundResource(R.drawable.blueborder);
                if(track_part_array[prog+1]==2) {
                    int present_score = Integer.parseInt(score_field.getText().toString());
                    present_score++;
                    score_field.setText(String.valueOf(present_score));
                }else{
                    Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
                }


                new CountDownTimer(800, 800) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        second_layout.setBackgroundResource(R.drawable.border);

                    }
                }.start();

            }
        });

        tap_three_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                third_layout.setBackgroundResource(R.drawable.greenborder);

                if(track_part_array[prog+1]==3) {
                    int present_score = Integer.parseInt(score_field.getText().toString());
                    present_score++;
                    score_field.setText(String.valueOf(present_score));
                    //third_layout.getChildAt(third_layout.getChildCount()-1).clearAnimation();
                }else{
                    Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT);
                }


                new CountDownTimer(800, 800) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
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
                case 1: {
                    progress += 1000;
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            //Start your animation here
                            addElements(first_layout, 400);

                        }
                    }, progress);
                }


                case 2: {
                    progress += 1000;
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            //Start your animation here
                            addElements(second_layout, 300);
                        }
                    }, progress);
                }


                case 3: {
                    progress += 1000;
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            //Start your animation here
                            addElements(third_layout, 200);
                        }
                    }, progress);

                }

            }

        }


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {

        }
    }

    public void addElements(ConstraintLayout layout, int heigh){

            TextView test=new TextView(this);
            test.setBackgroundResource(R.drawable.note);
            test.setText("hey");
            test.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, heigh));
            layout.addView(test);
            startAnimation(test, layout);

    }



    public void startAnimation(TextView rectangle, ConstraintLayout layout ){
            Animation animation = new TranslateAnimation(0, 0,0, layout.getHeight() );
            animation.setDuration(2000);
            rectangle.startAnimation(animation);
            rectangle.setVisibility(View.INVISIBLE);



    }



}
