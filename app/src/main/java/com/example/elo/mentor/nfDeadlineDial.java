package com.example.elo.mentor;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;

public class nfDeadlineDial extends AppCompatActivity {
    int deadline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nf_deadline_dial);

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
                    case R.id.radio_1h:
                        deadline = 1;
                        break;
                    case R.id.radio_4h:
                        deadline = 4;
                        break;
                    case R.id.radio_8h:
                        deadline = 8;
                        break;
                    case R.id.radio_24h:
                        deadline = 24;
                        break;
                    case R.id.radio_off:
                        deadline = 0;
                        break;
                }
            }
        });
    }
}
