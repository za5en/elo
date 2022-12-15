package com.example.elo.admin.profile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.admin.adminConstructor;

import java.io.IOException;

public class reports extends AppCompatActivity {
    Button copy;
    final Context context = this;
    DatabaseHelper DbHelper;
    Cursor c = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reports);
        DbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = DbHelper.getWritableDatabase();

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
                        Toast.makeText(context,
                                "elo_id: " + c.getString(0) + "\n" +
                                        "elo_name: " + c.getString(1) + "\n" +
                                        "elo_info: " + c.getString(2) + "\n" +
                                        "elo_availability:  " + c.getString(3) + "\n" +
                                        "elo_owner_id: " + c.getString(4) + "\n" +
                                        "elo_tag1: " + c.getString(5) + "\n" +
                                        "elo_tag2: " + c.getString(6) + "\n" +
                                        "elo_tag3: " + c.getString(7),
                                Toast.LENGTH_LONG).show();
                    } while (c.moveToNext());
                }
            }
        });
    }
}
