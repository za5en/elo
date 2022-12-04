package com.example.elo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class constructor extends AppCompatActivity {
    final Context context = this;
    Button tags, employees, tasks;
    ImageButton home, search, notifications, create;
    CheckBox isPrivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constructor);

        tags = findViewById(R.id.buttonTags);
        employees = findViewById(R.id.buttonEmpl);
        tasks = findViewById(R.id.buttonTasks);
        home = findViewById(R.id.homeButton);
        create = findViewById(R.id.create);
        search = findViewById(R.id.searchButton);
        notifications = findViewById(R.id.notificationsButton);
        isPrivate = findViewById(R.id.checkboxPrivate);

        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, tags.class);
                startActivity(intent);
            }
        });

        employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPrivate.isChecked())
                    employees.setEnabled(true);
                else {
                    Intent intent = new Intent(context, employees.class);
                    startActivity(intent);
                }
            }
        });

        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, tasks.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, search.class);
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, notifications.class);
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(context, addEloConfirm.class);
                startActivity(intent);
            }
        });
    }
}
