package com.example.elo.mentor.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;
import com.example.elo.mentor.eloPage.continueElo;
import com.example.elo.mentor.manage.pages.acceptTask;
import com.example.elo.mentor.manage.pages.requests;
import com.example.elo.worker.workerProfile;

import java.util.Locale;

public class notificationPage extends AppCompatActivity {
    final Context context = this;
    ImageButton back, button;
    String text, eloDesc, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_page);

        TextView nfTheme = findViewById(R.id.nf_theme);
        TextView nfText = findViewById(R.id.nf_desc);
        TextView nfDate = findViewById(R.id.nf_date);

        nfTheme.setText(getIntent().getStringExtra("theme"));
        nfText.setText(getIntent().getStringExtra("fullText"));
        nfDate.setText(getIntent().getStringExtra("date"));
        text = getIntent().getStringExtra("text");
        eloDesc = getIntent().getStringExtra("eloDesc");
        name = nfTheme.getText().toString();

        back = findViewById(R.id.backButton);
        button = findViewById(R.id.button);

        if (text.equals("Сотрудник выполнил задание")) {
            button.setImageResource(R.drawable.manage);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, acceptTask.class);
                    intent.putExtra("eloName", name);
                    startActivity(intent);
                }
            });
        }
        else if (text.equals("Новая заявка на вступление")) {
            button.setImageResource(R.drawable.requests);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, requests.class);
                    intent.putExtra("eloName", name);
                    startActivity(intent);
                }
            });
        }
        else if (text.contains("Уведомление о дедлайне")
                || text.equals("Выполнение задания подтверждено")) {
            button.setImageResource(R.drawable.resource_continue);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, continueElo.class);

                    intent.putExtra("previewName", nfTheme.getText().toString());
                    intent.putExtra("previewDesc", eloDesc);

                    startActivity(intent);
                }
            });
        }
        else if (text.equals("Принята заявка на вступление")) {
            button.setImageResource(R.drawable.begin);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, continueElo.class);

                    intent.putExtra("previewName", nfTheme.getText().toString());
                    intent.putExtra("previewDesc", eloDesc);

                    startActivity(intent);
                }
            });
        }
        else if (text.equals("Появился новый доступный ЭлО")) {
            button.setImageResource(R.drawable.request);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Заявка подана", Toast.LENGTH_SHORT).show();
                    button.setImageResource(R.drawable.request_off);
                }
            });
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
