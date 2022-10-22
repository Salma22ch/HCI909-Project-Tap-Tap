package com.example.tap_tap;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


public class ExpertModeActivity extends Activity {


    // model
    GameModel model_expert_game;

    // media player
    MediaPlayer mp;

    // Widget and components declaration
    Button pause_btn;

    TextView score_field;
    TextView best_score;
    TextView startCountDown;
    TextView game_over;
    AnimationDrawable animation_one;

    ConstraintLayout first_layout;
    ConstraintLayout second_layout;
    ConstraintLayout third_layout;

    // track details

    int track_duration;
    int[][] track_part_array;
    int track_part_number;

    // progress bar + timer to update the prgress bar
    ProgressBar progressbar;
    TextView timer_pg_bar;
    int prog=1;
    private CountDownTimer timer = null;
    private boolean paused;
    private  boolean finished;
    private  CountDownTimer progress_bar_timer;
    private ArrayList<ObjectAnimator> animator_set=new ArrayList<>();
    private  SharedPreferences sharedPref ;
    private String highscore="";


    // song-array





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        // media player
        mp=MediaPlayer.create(ExpertModeActivity.this, R.raw.furshort);


        //game model
        model_expert_game=new GameModel("expertMode",false,false,mp.getDuration());


        // buttons
        pause_btn=(Button)findViewById(R.id.btn_pause);

        // score + best score field
        score_field=(TextView)findViewById(R.id.score_field_game);
        best_score=(TextView)findViewById(R.id.highest_score);
        startCountDown=(TextView)findViewById(R.id.startcountdown);
        game_over=(TextView)findViewById(R.id.game_over);


        // layout
        first_layout=(ConstraintLayout) findViewById(R.id.first_layout);
        second_layout=(ConstraintLayout) findViewById(R.id.second_layout);
        third_layout=(ConstraintLayout) findViewById(R.id.third_layout);


        // progress bar timer parameters
        timer_pg_bar=findViewById(R.id.timer);
        progressbar=findViewById(R.id.music_progress_bar);


        // get height score
        sharedPref = getSharedPreferences("HIGH_SCORE",Context.MODE_PRIVATE);
        highscore= sharedPref.getString("expertMode", "defaultvalu");
        best_score.setText(highscore);




        first_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first_layout.setBackgroundResource(R.drawable.redborder);
                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score--;
                score_field.setText(String.valueOf(present_score));

                new CountDownTimer(300, 300) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        first_layout.setBackgroundResource(R.drawable.border);
                    }
                }.start();


            }
        });



        second_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                second_layout.setBackgroundResource(R.drawable.redborder);
                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score--;
                score_field.setText(String.valueOf(present_score));

                new CountDownTimer(300, 300) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        second_layout.setBackgroundResource(R.drawable.border);

                    }
                }.start();

            }
        });

        third_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                third_layout.setBackgroundResource(R.drawable.redborder);
                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score--;
                score_field.setText(String.valueOf(present_score));

                new CountDownTimer(300, 300) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        third_layout.setBackgroundResource(R.drawable.border);
                    }
                }.start();
            }
        });

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model_expert_game.setState_paused(!model_expert_game.getState_paused());
                if(model_expert_game.getState_paused()) {
                    pauseGame();
                    Drawable resume_d=getDrawable(R.drawable.ic_resume);
                    pause_btn.setBackground(resume_d);
                } else {
                    resumeGame();
                    Drawable pause_d=getDrawable(R.drawable.ic_pause_circle);
                    pause_btn.setBackground(pause_d);
                }

                if(model_expert_game.getState_finished()){
                    startActivity(new Intent(ExpertModeActivity.this, ExpertModeActivity.class));
                    model_expert_game.setState_paused(false);
                }

            }
        });

        // double tap



    }

    @Override
    protected void onStart() {
        super.onStart();
        Handler handler = new Handler();



        new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
                model_expert_game.setState_paused(true);
                startCountDown.setVisibility(View.VISIBLE);
                int countdown = Integer.parseInt(startCountDown.getText().toString());
                countdown--;
                startCountDown.setText(String.valueOf(countdown));

            }
            public void onFinish() {
                startCountDown.setText("Start !");
                model_expert_game.setState_paused(false);
                startCountDown.setVisibility(View.INVISIBLE);
                startCountDown.setText("4");
                mp.start();
                startTimer (0);

            }
        }.start();




        int i=0;
        while(i<model_expert_game.getTrack_part_array().length) {
            if(!model_expert_game.getState_paused()){
                int lay = model_expert_game.getTrack_part_array()[i][0];
                int note_time = model_expert_game.getTrack_part_array()[i][1];
                int note_height = model_expert_game.getTrack_part_array()[i][2];
                switch (lay) {
                    case 1: {

                        handler.postDelayed(new Runnable() {
                            public void run() {
                                //Start your animation here
                                addElements(first_layout, note_height);

                            }
                        }, note_time);
                        break;
                    }


                    case 2: {

                        handler.postDelayed(new Runnable() {
                            public void run() {
                                //Start your animation here
                                addElements(second_layout, note_height);
                            }
                        }, note_time);
                        break;
                    }


                    case 3: {

                        handler.postDelayed(new Runnable() {
                            public void run() {
                                //Start your animation here
                                addElements(third_layout, note_height);
                            }
                        }, note_time);
                        break;

                    }

                }

                 i++;
                if(model_expert_game.getState_finished()) break;
        }
        }

    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        if(!model_expert_game.getState_paused()) {
            model_expert_game.setState_paused(true);
            pauseGame();
            // dialog
            new AlertDialog.Builder(this)
                    .setTitle("Exit the game")
                    .setMessage("Are you sure you want to exit the game?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(ExpertModeActivity.this, PickActivity.class));

                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.

                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            model_expert_game.setState_paused(false);
                            resumeGame();

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }else{
            super.onBackPressed();
        }
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {

        }
    }





    public void addElements(ConstraintLayout layout, int heigh){

        Button test=new Button(this);
        test.setBackgroundResource(R.drawable.note);
        test.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, heigh));
        layout.addView(test);
        startAnimation(test, layout);

    }



    public void startAnimation(TextView rectangle, ConstraintLayout layout ){

        ObjectAnimator translationY = ObjectAnimator.ofFloat(rectangle, "translationY", layout.getHeight());
        translationY.setInterpolator(new AccelerateInterpolator());
        translationY.setDuration(1000);
        translationY.start();
        animator_set.add(translationY);

        if(!model_expert_game.getState_paused())rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score++;
                score_field.setText(String.valueOf(present_score));
                rectangle.setVisibility(View.INVISIBLE);
            }
        });

        if(model_expert_game.getState_paused()) translationY.pause();

    }

    private void startTimer(long timerStartFrom) {
        progress_bar_timer =new CountDownTimer(63*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                prog++;
                int level=prog*1000*100/model_expert_game.getTrack_duration();
                progressbar.setProgress(level);
                timer_pg_bar.setText(String.valueOf(prog));

            }

            public void onFinish() {
                model_expert_game.setState_finished(true);
                progressbar.setProgress(100);
                mp.stop();
                first_layout.removeViews(1, first_layout.getChildCount() - 1);
                second_layout.removeViews(1, second_layout.getChildCount() - 1);
                third_layout.removeViews(1, third_layout.getChildCount() - 1);
                Drawable d_restart=getDrawable(R.drawable.ic_replay);
                pause_btn.setBackground(d_restart);
                game_over.setVisibility(View.VISIBLE);
                if(Integer.parseInt(score_field.getText().toString()) >Integer.parseInt(highscore)) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("expertMode", score_field.getText().toString());
                    editor.apply();
                    game_over.setText("New High \n Score");

                }

            }
        }.start();
    }

    public  void pauseGame(){
        //countdown
        progress_bar_timer.cancel();
        //mdiaplayer
        mp.pause();
        //animation
        System.out.println(animator_set.size());
        for(int i=0; i<animator_set.size();i++){
            animator_set.get(i).pause();
        }

    }

    public void resumeGame(){
        // countdown
        startTimer(prog);
        //mediaplayer
        mp.seekTo(mp.getCurrentPosition());
        mp.start();
        //animation
        for(int i=0; i<animator_set.size();i++){
            animator_set.get(i).resume();
        }
    }


}
