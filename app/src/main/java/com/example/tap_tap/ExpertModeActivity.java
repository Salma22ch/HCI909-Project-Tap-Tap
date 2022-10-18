package com.example.tap_tap;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.media.MediaPlayer.SEEK_NEXT_SYNC;


public class ExpertModeActivity extends Activity {
    Button pause_btn;

    TextView score_field;
    TextView best_score;
    AnimationDrawable animation_one;

    ConstraintLayout first_layout;
    ConstraintLayout second_layout;
    ConstraintLayout third_layout;

    // track details
    MediaPlayer mp;
    MediaPlayer vibrate_mp;
    int track_duration;
    int[][] track_part_array;




    int track_part_number;

    // progress bar + timer to update the prgress bar

    //progress bar
    ProgressBar progressbar;
    TextView timer_pg_bar;
    int prog=1;
    private CountDownTimer timer = null;
    private boolean paused=false;
    private  CountDownTimer progress_bar_timer;
    private int current_ps=0;
    private ArrayList<ObjectAnimator> animator_set=new ArrayList<>();
    private String strjsonfile=null;
    private String highscore="";

    // song-array

    int[][] a = {{1, 1000, 300}, {2, 1400, 200},{1,2000, 300}, {2, 2400, 200},{1, 3000, 300}, {3, 3400, 200},{2, 3800, 200},{1, 4200, 400},{3, 4800, 600}};

    String[] ab={"e","d","e","d","e","b","d","c","a","a","c","e","a","b","e","e","d","b","c","a",
            "e","e","d","e","d","e","b","d","c","a","a","c","e","a","b","e","e","e","b","a","a",
            "e","d","e","d","e","b","d","c","a","a","c","e","a","b","e","e","e","d","b","c","a",
            "e","e","d","e","d","e","b","d","c","a","a","c","e","a","b","e","e","b","a","a",
            "c","e","a","b","e","e","c","b","a","a","b","c","d","e","c","g","f","e","d","g",
            "f","e","d","c","a","e","d","c","b","e","e","f","f","e","e","d","e","d","e",
            "d","e","d","e","d","e","b","d","c","a","a","c","e","a","b","e","a","b","e",
            "e","g","b","c","a","e","e","d","e","d","e","b","d","c","a","a","c","e","a","b",
            "e","e","c","b","a","a","b","c","d","e","c","g","f","e","d","g","f"};
    int[] rythm= {1, 2, 1, 2, 1, 3, 2, 2, 2, 2, 2, 1, 2, 3, 1, 1, 2, 3, 2, 2,
            1, 1, 2, 1, 2, 1, 3, 2, 2, 2, 2, 2, 1, 2, 3, 1, 1, 1, 3, 2, 2,
            1, 2, 1, 2, 1, 3, 2, 2, 2, 2, 2, 1, 2, 3, 1, 1, 1, 2, 3, 2, 2,
            1, 1, 2, 1, 2, 1, 3, 2, 2, 2, 2, 2, 1, 2, 3, 1, 1, 3, 2, 2,
            2, 1, 2, 3, 1, 1, 2, 3, 2, 2, 3, 2, 2};
    int [] intensite={ 300 , 200 , 300 , 200 , 300 , 300 , 200 , 300 , 400 , 500 , 300 , 300 , 300 , 400 , 500 , 300 , 300 , 200 , 300 , 400 , 500 ,
            300 , 300 , 200 , 300 , 200 , 300 , 300 , 200 , 300 , 400 , 500 , 300 , 300 , 300 , 500 , 300 , 300 , 300 , 300 , 400 , 500 ,
            300 , 200 , 300 , 200 , 300 , 300 , 200 , 300 , 400 , 500 , 300 , 300 , 300 , 400 , 500 ,  300 , 200 , 300 , 400 , 500 ,
            300 , 300 , 200 , 300 , 200 , 300 , 300 , 200 , 300 , 400 , 500 , 300 , 300 , 300 , 400 , 500 , 300 , 300 , 400 , 400 ,
            300 , 300 , 300 , 400 , 500 , 300 , 300 , 300 , 400 , 500 , 300 , 300 , 200
    };



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);

        // buttons
        pause_btn=(Button)findViewById(R.id.btn_pause);

        // score + best score field
        score_field=(TextView)findViewById(R.id.score_field_game);
        best_score=(TextView)findViewById(R.id.highest_score);

        // media player
        mp=MediaPlayer.create(ExpertModeActivity.this, R.raw.furshort);
        //mp.setPlaybackParams(mp.getPlaybackParams().setSpeed((float) 0.95));
        track_duration=mp.getDuration();
        track_part_number=(int)(track_duration/1000);
        track_part_array=new int[rythm.length][3];

        int p=100;
        for(int i=0;i<rythm.length;i++)
        {
            track_part_array[i][0] = rythm[i];
            track_part_array[i][1] = p;
            track_part_array[i][2] = intensite[i];
            if(i!=0 && rythm[i]==rythm[i-1])
                p+=1000;
            else p+=500;
        }


        // layout
        first_layout=(ConstraintLayout) findViewById(R.id.first_layout);
        second_layout=(ConstraintLayout) findViewById(R.id.second_layout);
        third_layout=(ConstraintLayout) findViewById(R.id.third_layout);


        //progress bar
        mp.start();
        timer_pg_bar=findViewById(R.id.timer);
        progressbar=findViewById(R.id.music_progress_bar);
        // timer for the progress bar
        startTimer(0);

        // high score
        highscore=loadHighScore();
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
                paused=!paused;
                if(paused) {
                    pauseGame();
                    Drawable resume_d=getDrawable(R.drawable.ic_resume);
                    pause_btn.setBackground(resume_d);
                } else {
                    resumeGame();
                    Drawable pause_d=getDrawable(R.drawable.ic_pause_circle);
                    pause_btn.setBackground(pause_d);
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Handler handler = new Handler();

        int i=0;
        while(i<track_part_array.length) {
            if(!paused){
                int lay = track_part_array[i][0];
                int note_time = track_part_array[i][1];
                int note_height = track_part_array[i][2];
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
        }
        }

    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        paused=true;
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
                        paused=false;
                        resumeGame();

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {

        }
    }

    public String loadHighScore() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("hscore.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONObject jsonobj=new JSONObject(json);
            return jsonobj.get("expertMode").toString();
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public void addElements(ConstraintLayout layout, int heigh){

        Button test=new Button(this);
        test.setBackgroundResource(R.drawable.note);
        test.setText("hey");
        test.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, heigh));
        layout.addView(test);
        startAnimation(test, layout);

    }



    public void startAnimation(TextView rectangle, ConstraintLayout layout ){

        ObjectAnimator translationY = ObjectAnimator.ofFloat(rectangle, "translationY", layout.getHeight());
        translationY.setInterpolator(new AccelerateInterpolator());
        translationY.setDuration(2000);
        translationY.start();
        animator_set.add(translationY);

        if(!paused)rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int present_score = Integer.parseInt(score_field.getText().toString());
                present_score++;
                score_field.setText(String.valueOf(present_score));
                rectangle.setVisibility(View.INVISIBLE);
            }
        });

        if(paused) translationY.pause();

    }

    private void startTimer(long timerStartFrom) {
        progress_bar_timer =new CountDownTimer(track_duration, 1000) {

            public void onTick(long millisUntilFinished) {
                prog++;
                int level=prog*1000*100/track_duration;
                progressbar.setProgress(level);
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
    }

    public  void pauseGame(){
        //countdown
        progress_bar_timer.cancel();
        //mdiaplayer
        //get current position
        current_ps=mp.getCurrentPosition();
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
