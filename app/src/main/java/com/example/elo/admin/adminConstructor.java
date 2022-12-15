package com.example.elo.admin;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.mentor.constructorElo.confirmation.addEloConfirm;

public class adminConstructor extends AppCompatActivity {
    final Context context = this;
    Button tags, employees, tasks;
    ImageButton home, search, notifications, create;
    CheckBox isPrivate;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    int elo_availability = 0;
    EditText name, info;

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

        name = findViewById(R.id.nameType);
        info = findViewById(R.id.descType);
        name.clearFocus();
        info.clearFocus();

        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

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
                if (isPrivate.isChecked()) {
                    employees.setEnabled(true);
                    elo_availability = 1;
                }
                else {
                    elo_availability = 0;
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
                Cursor cursor = db.query(DatabaseHelper.DB_ELO, null, null, null, null, null, null);
                String elo_name = name.getText().toString();
                String elo_info = info.getText().toString();
                String elo_tag1 = "java";
                String elo_tag2 = "back";
                String elo_tag3 = "sql";
                contentValues.put(DatabaseHelper.USER_TYPE, elo_name);
                contentValues.put(DatabaseHelper.USER_NAME, elo_info);
                contentValues.put(DatabaseHelper.USER_SURNAME, elo_availability);
                contentValues.put(DatabaseHelper.USER_EMAIL, elo_tag1);
                contentValues.put(DatabaseHelper.USER_PASSWORD, elo_tag2);
                contentValues.put(DatabaseHelper.USER_LEVEL, elo_tag3);
                db.insert(DatabaseHelper.DB_ELO, null, contentValues);
                cursor.close();
                finish();
                Intent intent = new Intent(context, addEloConfirm.class);
                startActivity(intent);
            }
        });
    }
}
