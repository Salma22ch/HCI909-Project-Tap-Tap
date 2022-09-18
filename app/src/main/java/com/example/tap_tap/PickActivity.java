package com.example.tap_tap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class PickActivity extends AppCompatActivity {


        ListView songs_list;
        ImageButton btn_previous;
        ImageButton btn_next;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pick);


            // list view + adapter
            songs_list=(ListView)findViewById(R.id.listOfSongs);
            ArrayList<String> songs=new ArrayList<>();
            songs.add("trak 1");
            songs.add("trak 2");
            songs.add("trak 3");
            songs.add("trak 4");
            ArrayAdapter songsAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,songs);
            songs_list.setAdapter(songsAdapter);

            // Buttons
            btn_previous= (ImageButton) findViewById(R.id.previous);
            btn_next= (ImageButton) findViewById(R.id.next);

            btn_previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PickActivity.this, MainActivity.class));
                }
            });

            btn_previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PickActivity.this, MainActivity.class));
                }
            });
    }



}

