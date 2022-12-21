package com.example.elo.mentor.eloPage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class EloInfo extends AppCompatActivity {

    final Context context = this;
    ImageButton back, take_part;
    RecyclerView tagRecycler;
    tagAdapter tagAdapter;
    List<tagCategory> categoryList;
    String name;
    int isPrivate;

    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String[] columns = {null};
    String selection = null;
    String[] selectionArgs = null;

    int eloId, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elo_info);

        userId = getIntent().getIntExtra("userId", 2);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues contentValues1 = new ContentValues();

        TextView eloName = findViewById(R.id.elo_name);
        TextView eloDescText = findViewById(R.id.elo_desc_text);

        eloId = getIntent().getIntExtra("id", 1);
        take_part = findViewById(R.id.take_part);

        String eloGetName = null;
        String eloInfo = null;
        int eloAvailability = 0;
        String eloTag1 = null;
        String eloTag2 = null;
        String eloTag3 = null;

        columns = new String[] { "elo_name", "elo_info", "elo_availability", "elo_tag1", "elo_tag2", "elo_tag3" };
        selection = "elo_id = ?";
        selectionArgs = new String[] { String.valueOf(eloId) };
        Cursor cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                eloGetName = cursor.getString(0);
                eloInfo = cursor.getString(1);
                eloAvailability = cursor.getInt(2);
                eloTag1 = cursor.getString(3);
                eloTag2 = cursor.getString(4);
                eloTag3 = cursor.getString(5);
            }
            cursor.close();
        }

        eloDescText.setText(eloInfo);
        eloName.setText(eloGetName);
        name = eloName.getText().toString();
        isPrivate = eloAvailability;
        if (isPrivate == 0)
        {
            take_part.setImageResource(R.drawable.request);
        }

        List<tagCategory> categoryList = new ArrayList<>();
        categoryList.add(new tagCategory(1, eloTag1));
        categoryList.add(new tagCategory(2, eloTag2));
        categoryList.add(new tagCategory(3, eloTag3));

        setCategoryRecycler(categoryList);

        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        take_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                take_part.setEnabled(false);
                if (isPrivate == 1) {
                    Toast.makeText(context, "ЭлО добавлен", Toast.LENGTH_SHORT).show();
                    take_part.setImageResource(R.drawable.takepartunavailable);
                    //add new elo to user table
                    Cursor cursor = db.query(DatabaseHelper.DB_REL_LIST, null, null, null, null, null, null);
                    contentValues.put(DatabaseHelper.USER_ID, userId);
                    contentValues.put(DatabaseHelper.ELO_ID, eloId);
                    db.insert(DatabaseHelper.DB_REL_LIST, null, contentValues);
                    cursor.close();
                    //
                    cursor = db.query(DatabaseHelper.DB_TASK_PROGRESS, null, null, null, null, null, null);
                    int taskId = 1, progress = 0;
                    contentValues1.put(DatabaseHelper.USER_ID, userId);
                    contentValues1.put(DatabaseHelper.ELO_ID, eloId);
                    contentValues1.put(DatabaseHelper.TASK_ID, taskId);
                    contentValues1.put(DatabaseHelper.PROGRESS, progress);
                    db.insert(DatabaseHelper.DB_TASK_PROGRESS, null, contentValues);
                    cursor.close();
                }
                else {
                    Toast.makeText(context, "Заявка подана", Toast.LENGTH_SHORT).show();
                    take_part.setImageResource(R.drawable.request_off);
                }
            }
        });
    }

    private void setCategoryRecycler(List<tagCategory> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        tagRecycler = findViewById(R.id.tagRecycler);
        tagRecycler.setLayoutManager(layoutManager);
        tagAdapter = new tagAdapter(this, categoryList);
        tagRecycler.setAdapter(tagAdapter);
    }
}