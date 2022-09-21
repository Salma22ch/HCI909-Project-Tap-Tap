package com.example.tap_tap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class EasyModeActivity extends Activity {
    Button tap_one_btn ;
    Button tap_two_btn ;
    Button tap_three_btn ;

    TextView score_field;

    ConstraintLayout first_layout;
    ConstraintLayout second_layout;
    ConstraintLayout third_layout;

    MediaPlayer mp;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_2);

        tap_one_btn=(Button)findViewById(R.id.tap_one_btn);
        tap_two_btn=(Button)findViewById(R.id.tap_two_btn);
        tap_three_btn=(Button)findViewById(R.id.tap_three_btn);

        score_field=(TextView)findViewById(R.id.score_field_game);
        //mp=MediaPlayer.create(this, R.raw);

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
                mp.start();
            }
        });

        tap_two_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                second_layout.setBackgroundColor(Color.BLUE);
                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score++;
                score_field.setText(String.valueOf(present_score));
                mp.start();
            }
        });

        tap_three_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                third_layout.setBackgroundColor(Color.GREEN);
                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score++;
                score_field.setText(String.valueOf(present_score));
                mp.start();
            }
        });
    }
}
