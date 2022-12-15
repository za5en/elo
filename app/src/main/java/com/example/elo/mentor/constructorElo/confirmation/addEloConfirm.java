package com.example.elo.mentor.constructorElo.confirmation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;

public class addEloConfirm extends AppCompatActivity {
    TextView eloName, eloDesc;
    Button tags, employees, tasks;
    ImageButton confirm, cancel;
    final Context context = this;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_elo_confirm);

        eloName = findViewById(R.id.eloName);
        eloDesc = findViewById(R.id.eloDesc);
        tags = findViewById(R.id.buttonTags);
        employees = findViewById(R.id.buttonEmpl);
        tasks = findViewById(R.id.buttonTasks);
        confirm = findViewById(R.id.confirm);
        cancel = findViewById(R.id.cancel);

        eloName.setText("Java для начинающих");
        eloDesc.setText("Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java,\nв первую очередь для работы\nс сервером");

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "ЭлО создан", Toast.LENGTH_SHORT).show();
                finish();
                finish();
            }
        });

        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, tagsConfirm.class);
                startActivity(intent);
            }
        });

        employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, employeesConfirm.class);
                startActivity(intent);
            }
        });

        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, tasksConfirm.class);
                startActivity(intent);
            }
        });
    }
}
