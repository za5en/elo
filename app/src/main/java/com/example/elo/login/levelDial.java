package com.example.elo.login;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;

public class levelDial extends AppCompatActivity {
    String user_level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_dial);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        SharedPreferences checked = getSharedPreferences("check", 0);

        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        break;
                    case R.id.radio_jun:
                        user_level = "junior";
                        break;
                    case R.id.radio_mid:
                        user_level = "middle";
                        break;
                    case R.id.radio_sen:
                        user_level = "senior";
                        break;
                }
            }
        });
    }
}
