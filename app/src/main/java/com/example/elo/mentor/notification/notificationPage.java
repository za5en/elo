package com.example.elo.mentor.notification;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;

public class notificationPage extends AppCompatActivity {
    final Context context = this;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_page);

        TextView nfTheme = findViewById(R.id.nf_theme);
        TextView nfText = findViewById(R.id.nf_desc);

        nfTheme.setText(getIntent().getStringExtra("theme"));
        nfText.setText(getIntent().getStringExtra("fullText"));

        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
