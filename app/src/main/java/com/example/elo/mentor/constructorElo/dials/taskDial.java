package com.example.elo.mentor.constructorElo.dials;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;

public class taskDial extends AppCompatActivity {
    EditText task, desc, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_dial);
        task = findViewById(R.id.task_type);
        desc = findViewById(R.id.desc_type);
        url = findViewById(R.id.url_type);

        task.clearFocus();
        desc.clearFocus();
        url.clearFocus();
    }
}
