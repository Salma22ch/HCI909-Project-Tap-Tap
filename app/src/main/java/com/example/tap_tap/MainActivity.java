package com.example.tap_tap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_play ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play=(Button)findViewById(R.id.play);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PickActivity.class));
            }
        });

        SharedPreferences sharedPref = this.getSharedPreferences("HIGH_SCORE",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(!sharedPref.contains("easyMode")) {
            editor.putString("easyMode", "0");
        }
        if(!sharedPref.contains("mediumMode")) {
            editor.putString("mediumMode", "0");
        }
        if(!sharedPref.contains("expertMode")) {
            editor.putString("expertMode", "0");
        }
        editor.commit();
    }


}