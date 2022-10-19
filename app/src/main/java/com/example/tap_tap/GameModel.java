package com.example.tap_tap;

import android.content.SharedPreferences;

public class GameModel {

    private String game_mode;
    private String high_score;
    private boolean state_paused;
    private boolean state_finished;



    private  int track_duration;


    // song sequence + time  for synchronisation
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

    int[][] track_part_array=new int[rythm.length][3];;


    // constructor
    public GameModel(String game_mode,  boolean state_paused, boolean state_finished, int track_duration) {
        this.game_mode = game_mode;
        this.high_score = high_score;
        this.state_paused = state_paused;
        this.state_finished = state_finished;
        this.track_duration=track_duration;
    }

    // getters and setters

    public int getTrack_duration() {
        return track_duration;
    }

    public void setTrack_duration(int track_duration) {
        this.track_duration = track_duration;
    }

    public String getGame_mode() {
        return game_mode;
    }

    public void setGame_mode(String game_mode) {
        this.game_mode = game_mode;
    }

    public String getHigh_score() {
        return high_score;
    }

    public void setHigh_score(String high_score) {
        this.high_score = high_score;
    }

    public boolean getState_paused() {
        return state_paused;
    }

    public void setState_paused(boolean state_paused) {
        this.state_paused = state_paused;
    }

    public boolean getState_finished() {
        return state_finished;
    }

    public void setState_finished(boolean state_finished) {

        this.state_finished = state_finished;
    }



    public int[][] getTrack_part_array() {
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
        return track_part_array;
    }



    public void setTrack_part_array() {
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
    }


}
