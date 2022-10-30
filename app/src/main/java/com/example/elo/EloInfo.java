package com.example.elo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EloInfo extends AppCompatActivity {

    final Context context = this;
    ImageButton home, profile, take_part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elo_info);

        TextView eloName = findViewById(R.id.elo_name);
        TextView eloDescText = findViewById(R.id.elo_desc_text);

        eloDescText.setText(getIntent().getStringExtra("previewDesc"));
        eloName.setText(getIntent().getStringExtra("previewName"));

        home = findViewById(R.id.homeButton);
        profile = findViewById(R.id.profileButton);
        take_part = findViewById(R.id.take_part);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, profile.class);
                startActivity(intent);
            }
        });

        take_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "ЭлО добавлен", Toast.LENGTH_SHORT).show();
                take_part.setEnabled(false);
            }
        });
    }
}
