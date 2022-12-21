package com.example.elo.mentor.eloPage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import com.example.elo.mentor.eloPage.pass.eloPass;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class continueElo extends AppCompatActivity {

    final Context context = this;
    ImageButton back, continue_elo;
    RecyclerView tagRecycler;
    tagAdapter tagAdapter;
    List<tagCategory> categoryList;
    String name, taskName, taskDesc, url;
    int userId, eloId;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String[] columns = {null};
    String selection = null;
    String[] selectionArgs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continue_elo);

        userId = getIntent().getIntExtra("userId", 2);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

        TextView eloName = findViewById(R.id.elo_name);
        TextView eloDescText = findViewById(R.id.elo_desc_text);
        TextView percents = findViewById(R.id.percents);

        eloName.setText(getIntent().getStringExtra("previewName"));
        name = eloName.getText().toString();

        String eloInfo = null;
        String eloTag1 = null;
        String eloTag2 = null;
        String eloTag3 = null;

        columns = new String[] { "elo_id", "elo_info", "elo_tag1", "elo_tag2", "elo_tag3" };
        selection = "elo_name = ?";
        selectionArgs = new String[] { name };
        Cursor cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                eloId = cursor.getInt(0);
                eloInfo = cursor.getString(1);
                eloTag1 = cursor.getString(2);
                eloTag2 = cursor.getString(3);
                eloTag3 = cursor.getString(4);
            }
            cursor.close();
        }

        String userAmount = null, wholeAmount = null, amount;

        String task_count = "select count(*) from elo_tasks where elo_id = '" + eloId + "';";
        cursor = db.rawQuery(task_count, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                wholeAmount = cursor.getString(0);
            }
            cursor.close();
        }


        task_count = "select task_id from task_progress where user_id = '"+ userId +"' and elo_id = '"+ eloId +"';";
        cursor = db.rawQuery(task_count, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userAmount = cursor.getString(0);
            }
            cursor.close();
        }

        amount = userAmount + '/' + wholeAmount;
        percents.setText(amount);

        eloDescText.setText(eloInfo);

        List<tagCategory> categoryList = new ArrayList<>();
        categoryList.add(new tagCategory(1, eloTag1));
        categoryList.add(new tagCategory(2, eloTag2));
        categoryList.add(new tagCategory(3, eloTag3));

        if (name.equals("Java для начинающих")) {
            taskName = "Задание 1";
            taskDesc = "посмотреть видео:";
            url = "https://www.youtube.com/watch?v=U2OliQwRb6c&list=PLDyJYA6aTY1lT614ixLYq48har7EnCXpk";
        }
        else if (name.equals("Java для Senior")) {
            taskName = "Задание 1";
            taskDesc = "Immutable Collections. Просмотр видео:";
            url = "https://youtu.be/jMvF2zs7ApA";
        }
        else if (name.equals("Основы Python")) {
            taskName = "Задание 1";
            taskDesc = "Изучение 1 главы:";
            url = "https://metanit.com/python/tutorial/1.1.php";
        }
        else if (name.equals("Нейросети в Python")) {
            taskName = "Итоговое задание";
            taskDesc = "Просмотр курса по машинному обучению:";
            url = "https://youtu.be/GwIo3gDZCVQ";
        }
        else if (name.toLowerCase().contains("front&back")) {
            taskName = "Задание 1";
            taskDesc = "Ознакомиться с материалом:";
            url = "https://stackoverflow.com/questions/68164444/how-to-connect-backend-and-frontend";
        }
        else if (name.toLowerCase().contains("c#")) {
            taskName = "Задание 1";
            taskDesc = "Изучение 1 главы:";
            url = "https://metanit.com/sharp/tutorial/1.1.php";
        }
        else if (name.toLowerCase().contains("sql")) {
            taskName = "Задание 1";
            taskDesc = "Посмотреть видео и попрактиковаться:";
            url = "https://www.youtube.com/watch?v=sLwiFGAOMK4&list=PLqj7-hRTFl_oweCD2cFQYdJDmD5bwEhb9";
        }
        else if (name.toLowerCase().contains("frontend")) {
            taskName = "Задание 1";
            taskDesc = "Посмотреть видео:";
            url = "https://youtu.be/cBYDQlwGEjQ";
        }

        setCategoryRecycler(categoryList);

        continue_elo = findViewById(R.id.continueElo);

        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        continue_elo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, eloPass.class);

                intent.putExtra("taskName", taskName);
                intent.putExtra("taskDesc", taskDesc);
                intent.putExtra("url", url);
                intent.putExtra("userId", userId);
                intent.putExtra("eloId", eloId);

                context.startActivity(intent);
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