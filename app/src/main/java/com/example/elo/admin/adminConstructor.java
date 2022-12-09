package com.example.elo.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;
import com.example.elo.mentor.constructorElo.confirmation.addEloConfirm;

public class adminConstructor extends AppCompatActivity {
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
        search = findViewById(R.id.searchButton);
        notifications = findViewById(R.id.notificationsButton);
        create = findViewById(R.id.create);
        isPrivate = findViewById(R.id.checkboxPrivate);

        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.constructorElo.tags.class);
                startActivity(intent);
            }
        });

        employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPrivate.isChecked())
                    employees.setEnabled(true);
                else {
                    Intent intent = new Intent(context, com.example.elo.mentor.constructorElo.employees.class);
                    startActivity(intent);
                }
            }
        });

        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.constructorElo.tasks.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(context, adminMain.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, adminSearch.class);
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, adminNotifications.class);
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