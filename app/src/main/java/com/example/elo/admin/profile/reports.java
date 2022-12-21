package com.example.elo.admin.profile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.admin.adminConstructor;
import com.example.elo.admin.profile.report.rElo;
import com.example.elo.admin.profile.report.rTasks;
import com.example.elo.admin.profile.report.rUsers;

import java.io.IOException;

public class reports extends AppCompatActivity {
    Button copy, elo, users, tasks;
    final Context context = this;
    DatabaseHelper DbHelper;
    Cursor c = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reports);
        DbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = DbHelper.getWritableDatabase();

        ImageButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button elo = findViewById(R.id.r_elo);
        elo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, rElo.class);
                startActivity(intent);
            }
        });

        Button users = findViewById(R.id.r_users);
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, rUsers.class);
                startActivity(intent);
            }
        });

        Button tasks = findViewById(R.id.r_tasks);
        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, rTasks.class);
                startActivity(intent);
            }
        });

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


        copy = findViewById(R.id.db_copy);
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DbHelper.createDatabase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
                DbHelper.openDatabase();
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                c = DbHelper.query_s("elo", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        Log.v("Log: ",
                                "elo_id: " + c.getString(0) + "\n" +
                                        "elo_name: " + c.getString(1) + "\n" +
                                        "elo_info: " + c.getString(2) + "\n" +
                                        "elo_availability:  " + c.getString(3) + "\n" +
                                        "elo_owner_id: " + c.getString(4) + "\n" +
                                        "elo_tag1: " + c.getString(5) + "\n" +
                                        "elo_tag2: " + c.getString(6) + "\n" +
                                        "elo_tag3: " + c.getString(7));
                    } while (c.moveToNext());
                }
            }
        });



    }
}
