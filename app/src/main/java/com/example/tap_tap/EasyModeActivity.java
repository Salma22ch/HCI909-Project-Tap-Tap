package com.example.tap_tap;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;

public class EasyModeActivity extends Activity {
    Button tap_one_btn ;
    Button tap_two_btn ;
    Button tap_three_btn ;

    TextView score_field;

    ConstraintLayout first_layout;
    ConstraintLayout second_layout;
    ConstraintLayout third_layout;

    MediaPlayer mp_one, mp_two, mp_three, mp;
    int currentPosition=0;



   // for songs bar
    private CountDownTimer timer = null;
    //mediaPlayer.setDataSource(PATH);
    //mediaPlayer.prepare();
    //mediaPlayer.start();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        tap_one_btn=(Button)findViewById(R.id.tap_one_btn);
        tap_two_btn=(Button)findViewById(R.id.tap_two_btn);
        tap_three_btn=(Button)findViewById(R.id.tap_three_btn);

        score_field=(TextView)findViewById(R.id.score_field_game);

        mp=MediaPlayer.create(EasyModeActivity.this, R.raw.track);




        first_layout=(ConstraintLayout) findViewById(R.id.first_layout);
        second_layout=(ConstraintLayout) findViewById(R.id.second_layout);
        third_layout=(ConstraintLayout) findViewById(R.id.third_layout);

        tap_one_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first_layout.setBackgroundColor(Color.RED);
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
                        first_layout.setBackgroundColor(Color.WHITE);
                    }
                }.start();
            }
        });

        tap_two_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score++;
                score_field.setText(String.valueOf(present_score));
                second_layout.setBackgroundColor(Color.BLUE);
                currentPosition = mp.getCurrentPosition();
                //mp=MediaPlayer.create(EasyModeActivity.this, R.raw.track);
                mp.seekTo(currentPosition +1);
                mp.start();

                new CountDownTimer(1000, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        mp.pause();
                        second_layout.setBackgroundColor(Color.WHITE);

                    }
                }.start();

            }
        });

        tap_three_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                third_layout.setBackgroundColor(Color.GREEN);
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
                        third_layout.setBackgroundColor(Color.WHITE);
                    }
                }.start();
            }
        });
    }
}
