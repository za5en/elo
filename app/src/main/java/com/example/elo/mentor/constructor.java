package com.example.elo.mentor;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.mentor.constructorElo.confirmation.addEloConfirm;

public class constructor extends AppCompatActivity {
    final Context context = this;
    Button tags, employees, tasks;
    ImageButton home, search, notifications, create;
    CheckBox isPrivate;
    EditText name, desc;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    int elo_availability = 0;
    String[] columns = {null};
    String selection = null;
    String[] selectionArgs = null;

    int userId, eloId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constructor);

        userId = getIntent().getIntExtra("userId", 2);

        tags = findViewById(R.id.buttonTags);
        employees = findViewById(R.id.buttonEmpl);
        tasks = findViewById(R.id.buttonTasks);
        home = findViewById(R.id.homeButton);
        create = findViewById(R.id.create);
        search = findViewById(R.id.searchButton);
        notifications = findViewById(R.id.notificationsButton);
        isPrivate = findViewById(R.id.checkboxPrivate);
        name = findViewById(R.id.nameType);
        desc = findViewById(R.id.descType);

        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String[] tasks_name = {
                "Задание 1",
                "Задание 2",
                "Задание 3",
                "Задание 4",
                "Задание 5",
                "Задание 6",
                "Итоговое задание"};
        String[] tasks_info = {
                "Посмотреть видео: ",
                "Посмотреть видео: ",
                "Посмотреть видео: ",
                "Посмотреть видео: ",
                "Посмотреть видео: ",
                "Посмотреть видео: ",
                "решить тест: "};
        String[] tasks_url = {
                "https://youtu.be/GwIo3gDZCVQ",
                "https://youtu.be/GwIo3gDZCVQ",
                "https://youtu.be/GwIo3gDZCVQ",
                "https://youtu.be/GwIo3gDZCVQ",
                "https://youtu.be/GwIo3gDZCVQ",
                "https://youtu.be/GwIo3gDZCVQ",
                "https://youtu.be/GwIo3gDZCVQ"};

        name.clearFocus();
        desc.clearFocus();

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
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.search.class);
                //userId = getIntent().getIntExtra("userId", 2);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.notifications.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.query(DatabaseHelper.DB_ELO, null, null, null, null, null, null);
                //add elo to elo table
                String elo_name = name.getText().toString();
                String elo_info = desc.getText().toString();
                String elo_short_info = elo_info;
                int elo_owner_id = userId;
                String elo_tag1 = "java";
                String elo_tag2 = "back";
                String elo_tag3 = "sql";
                contentValues.put(DatabaseHelper.ELO_NAME, elo_name);
                contentValues.put(DatabaseHelper.ELO_SHORT_INFO, elo_short_info);
                contentValues.put(DatabaseHelper.ELO_INFO, elo_info);
                contentValues.put(DatabaseHelper.ELO_AVAILABILITY, elo_availability);
                contentValues.put(DatabaseHelper.ELO_OWNER_ID, elo_owner_id);
                contentValues.put(DatabaseHelper.ELO_TAG1, elo_tag1);
                contentValues.put(DatabaseHelper.ELO_TAG2, elo_tag2);
                contentValues.put(DatabaseHelper.ELO_TAG3, elo_tag3);
                db.insert(DatabaseHelper.DB_ELO, null, contentValues);
                cursor.close();
                //get elo id
                columns = new String[] { "elo_id" };
                selection = "elo_name = ?";
                selectionArgs = new String[] { elo_name };
                cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        eloId = cursor.getInt(0);
                    }
                }
                assert cursor != null;
                cursor.close();
                //add tasks to elo_tasks
                int tasks_size = 7;
                for (int i = 0; i < tasks_size; i++) {
                    String task_name = tasks_name[i];
                    String task_info = tasks_info[i];
                    String task_url = tasks_url[i];
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DatabaseHelper.ELO_ID, eloId);
                    contentValues.put(DatabaseHelper.TASK_NAME, task_name);
                    contentValues.put(DatabaseHelper.TASK_INFO, task_info);
                    contentValues.put(DatabaseHelper.TASK_URL, task_url);
                    db.insert(DatabaseHelper.DB_ELO_TASKS, null, contentValues);
                }

                Intent intent = new Intent(context, addEloConfirm.class);
                intent.putExtra("userId", userId);
                intent.putExtra("eloName", name.getText().toString());
                intent.putExtra("eloDesc", desc.getText().toString());
                finish();
                startActivity(intent);
            }
        });
    }
}
