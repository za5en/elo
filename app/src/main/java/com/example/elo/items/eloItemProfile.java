package com.example.elo.items;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;

public class eloItemProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elo_item_profile);

        ProgressBar progressBar = findViewById(R.id.progress);
        progressBar.setProgress(65);
        TextView textView = findViewById(R.id.percents);
        textView.setText(progressBar.getProgress()+" %");
    }
}
