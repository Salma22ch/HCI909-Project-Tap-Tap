package com.example.tap_tap;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;



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
    int currentPosition=0; // for media player

    TextView rectangle;

   // for songs bar
    private CountDownTimer timer = null;





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

        // layout
        first_layout=(ConstraintLayout) findViewById(R.id.first_layout);
        second_layout=(ConstraintLayout) findViewById(R.id.second_layout);
        third_layout=(ConstraintLayout) findViewById(R.id.third_layout);


        //addElements(first_layout);


        tap_one_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first_layout.setBackgroundResource(R.drawable.redborder);
                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score++;
                score_field.setText(String.valueOf(present_score));
                //mp_one=MediaPlayer.create(EasyModeActivity.this, R.raw.track);
                //mp=MediaPlayer.create(EasyModeActivity.this, R.raw.track);
                currentPosition = mp.getCurrentPosition();
                mp.seekTo(currentPosition +1);
                mp.start();


                new CountDownTimer(1000, 1000) {
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
                //mp=MediaPlayer.create(EasyModeActivity.this, R.raw.track);
                mp.seekTo(currentPosition +1);
                mp.start();

                new CountDownTimer(1000, 1000) {
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
                //mp=MediaPlayer.create(EasyModeActivity.this, R.raw.track);
                mp.seekTo(currentPosition +1);
                mp.start();



                new CountDownTimer(1000, 1000) {
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
        rectangle=addElements(first_layout);
        startAnimation(rectangle,first_layout);
        rectangle=addElements(second_layout);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {
            //addElements(first_layout);
            rectangle=addElements(first_layout);
            startAnimation(rectangle,first_layout);
            //addElements(second_layout);
            //addElements(third_layout);

        }
    }

    public TextView addElements(ConstraintLayout layout){
            TextView test=new TextView(this);
            test.setBackgroundResource(R.drawable.note);
            test.setText("hey");
            test.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 140));
            layout.addView(test);
            return test;

    }

    public void startAnimation(TextView rectangle, ConstraintLayout layout ){

            Animation animation = new TranslateAnimation(0, 0,0, layout.getHeight() - rectangle.getY() - rectangle.getHeight() - tap_one_btn.getHeight() * 2);
            animation.setDuration(1000);
            animation.setRepeatCount(Animation.INFINITE);
            rectangle.startAnimation(animation);

    }

}
