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
        Button btn_easy;
        Button btn_medium;
        Button btn_expert;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pick);




            // Buttons
            btn_previous= (ImageButton) findViewById(R.id.previous);
            btn_easy= (Button) findViewById(R.id.EasyMode);
            btn_medium= (Button) findViewById(R.id.MediumMode);
            btn_expert= (Button) findViewById(R.id.ExpertMode);

            btn_previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PickActivity.this, MainActivity.class));
                }
            });

            btn_easy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PickActivity.this, EasyModeActivity.class));
                }
            });

            btn_medium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PickActivity.this, MediumModeActivity.class));
                }
            });

            btn_expert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PickActivity.this, ExpertModeActivity.class));
                }
            });
    }



}

