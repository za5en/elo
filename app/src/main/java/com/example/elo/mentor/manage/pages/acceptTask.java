package com.example.elo.mentor.manage.pages;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.adapter.acceptTaskAdapter;
import com.example.elo.adapter.eloAdapter;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.AcceptTask;
import com.example.elo.model.Elos;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class acceptTask extends AppCompatActivity {
    RecyclerView tasksRecycler;
    static acceptTaskAdapter tasksAdapter;
    final Context context = this;

    static List<AcceptTask> tasks = new ArrayList<>();
    static List<AcceptTask> allTasks = new ArrayList<>();

    String name;

    int uId, id;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String[] columns = {null};
    String selection = null;
    String[] selectionArgs = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_task);
        ImageButton back = findViewById(R.id.backButton);

        name = getIntent().getStringExtra("eloName");

        uId = getIntent().getIntExtra("userId", 2);
        id = getIntent().getIntExtra("eloId", 1);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        int[] userId = new int[20];
        int[] taskId = new int[20];
        String[] userName = new String[20];

        int k = 0;
        columns = new String[] { "user_id", "task_id" };
        selection = "elo_id = ? AND progress = ?";
        selectionArgs = new String[] { String.valueOf(id), String.valueOf(1) };
        Cursor cursor = db.query(DatabaseHelper.DB_TASK_PROGRESS, columns, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    userId[k] = cursor.getInt(0);
                    taskId[k++] = cursor.getInt(1);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        tasks.clear();
        allTasks.clear();

        String userN = null, userS = null;
        columns = new String[] { "user_name", "user_surname" };
        selection = "user_id = ?";
        for (int i = 0; i < k; i++) {
            selectionArgs = new String[] { String.valueOf(userId[i]) };
            cursor = db.query(DatabaseHelper.DB_USERS, columns, selection, selectionArgs, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    userN = cursor.getString(0);
                    userS = cursor.getString(1);
                    userName[i] = userN + ' ' + userS;
                    tasks.add(new AcceptTask(userName[i],String.valueOf(taskId[i]), i, id, userId[i]));
                }
                cursor.close();
            }
        }

        TextView textView = findViewById(R.id.textView);
        TextPaint paint = textView.getPaint();
        float width = paint.measureText("tasks");
        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        getColor(R.color.dark),
                        getColor(R.color.middle),
                        getColor(R.color.light),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        if (name.toLowerCase().contains("java")) {
//            tasks.add(new AcceptTask("Артём Коновалов","Задание 5", 1));
//            tasks.add(new AcceptTask("RUSTAM GPOWER","Задание 3", 2));
//            tasks.add(new AcceptTask("Worker Name","Задание 1", 3));
//        }
//        else if (name.toLowerCase().contains("python")) {
//            tasks.add(new AcceptTask("Кирилл Широбоков","Задание 7", 1));
//            tasks.add(new AcceptTask("RUSTAM GPOWER","Задание 4", 2));
//            tasks.add(new AcceptTask("Максим Максим","Задание 1", 3));
//            tasks.add(new AcceptTask("Виталий Новый","Задание 5", 4));
//            tasks.add(new AcceptTask("Артём Коновалов","Задание 2", 5));
//            tasks.add(new AcceptTask("Дима Перевозчиков","Задание 3", 6));
//        }
//        else if (name.toLowerCase().contains("front&back")) {
//            tasks.add(new AcceptTask("Фёдор Власов","Задание 1", 1));
//        }
//        else if (name.toLowerCase().contains("c#")) {
//            tasks.add(new AcceptTask("Worker Name","Задание 1", 1));
//            tasks.add(new AcceptTask("Кирилл Широбоков","Задание 3", 2));
//            tasks.add(new AcceptTask("Виталий Новый","Задание 4", 3));
//        }
//        else if (name.toLowerCase().contains("sql")) {
//            tasks.add(new AcceptTask("Worker Name","Задание 1", 1));
//            tasks.add(new AcceptTask("Рустам Авангард","Итоговое задание", 2));
//        }
//        else if (name.toLowerCase().contains("frontend")) {
//            tasks.add(new AcceptTask("Worker Name","Задание 1", 1));
//            tasks.add(new AcceptTask("RUSTAM GPOWER","Задание 4", 2));
//            tasks.add(new AcceptTask("Артём Коновалов","Задание 4", 3));
//        }

        allTasks.addAll(tasks);
        setTaskRecycler(tasks);
    }

    private void setTaskRecycler(List<AcceptTask> tasks) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        tasksRecycler = findViewById(R.id.tasksRecycler);
        tasksRecycler.setLayoutManager(layoutManager);
        tasksAdapter = new acceptTaskAdapter(this, tasks);
        tasksRecycler.setAdapter(tasksAdapter);
    }

    public static void removeTasks(int id) {

        tasks.clear();
        tasks.addAll(allTasks);

        List<AcceptTask> filter = new ArrayList<>();

        for (AcceptTask c : tasks) {
            if(c.getId() != id)
                filter.add(c);
        }

        tasks.clear();
        tasks.addAll(filter);
        allTasks.clear();
        allTasks.addAll(filter);

        tasksAdapter.notifyDataSetChanged();
    }
}