package com.example.elo.mentor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.adapter.eloAdapter;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.Elos;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    RecyclerView tagRecycler, eloRecycler;
    tagAdapter tagAdapter;
    static eloAdapter eloAdapter;
    static List<Elos> eloList = new ArrayList<>();
    static List<Elos> allEloList = new ArrayList<>();

    ImageButton settings, search, notifications, profile, reset;

    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String[] columns = {null};
    String selection = null;
    String[] selectionArgs = null;

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = getIntent().getIntExtra("userId", 2);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

        TextView textView = findViewById(R.id.elo);
        TextPaint paint = textView.getPaint();
        float width = paint.measureText("elo");
        Shader textShader=new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        getColor(R.color.dark),
                        getColor(R.color.middle),
                        getColor(R.color.light),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

        List<tagCategory> categoryList = new ArrayList<>();
        categoryList.add(new tagCategory(1, "front"));
        categoryList.add(new tagCategory(2, "back"));
        categoryList.add(new tagCategory(3, "qa"));
        categoryList.add(new tagCategory(4, "java"));
        categoryList.add(new tagCategory(5, "python"));
        categoryList.add(new tagCategory(6, "c#"));
        categoryList.add(new tagCategory(7, "sql"));
        categoryList.add(new tagCategory(8, "react"));
        categoryList.add(new tagCategory(9, "другое"));
        categoryList.add(new tagCategory(10, "debug"));

        setCategoryRecycler(categoryList);

        int[] eloId = new int[20];
        String[] eloName = new String[20];
        String[] eloShortInfo = new String[20];
        String[] eloInfo = new String[20];
        int[] eloOwnerId = new int[20];
        String[] eloTag1 = new String[20];
        String[] eloTag2 = new String[20];
        String[] eloTag3 = new String[20];

        int k = 0;
        columns = new String[] { "elo_id", "elo_name", "elo_short_info", "elo_info", "elo_owner_id", "elo_tag1", "elo_tag2", "elo_tag3" };
        Cursor cursor = db.query(DatabaseHelper.DB_ELO, columns, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    eloId[k] = cursor.getInt(0);
                    eloName[k] = cursor.getString(1);
                    eloShortInfo[k] = cursor.getString(2);
                    eloInfo[k] = cursor.getString(3);
                    eloOwnerId[k] = cursor.getInt(4);
                    eloTag1[k] = cursor.getString(5);
                    eloTag2[k] = cursor.getString(6);
                    eloTag3[k++] = cursor.getString(7);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }


        eloList.clear();
        for (int i = 0; i < k; i++) {
            if (eloOwnerId[k] != userId) {
                if (i == 0 || i == 2 || i == 3 || i == 5) {
                    eloList.add(new Elos(eloId[i], eloName[i],
                            eloShortInfo[i],
                            eloInfo[i],
                            eloName[i], i + 1, false, userId,
                            eloTag1[i], eloTag2[i], eloTag3[i]));
                }
            }
        }

        allEloList.addAll(eloList);
        setEloRecycler(eloList);

        settings = findViewById(R.id.settingsButton);
        search = findViewById(R.id.searchButton);
        notifications = findViewById(R.id.notificationsButton);
        profile = findViewById(R.id.profileButton);
        reset = findViewById(R.id.resetButton);

        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.profile.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.search.class);
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

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetEloCategories(view);
            }
        });
    }

    public void resetEloCategories(View view) {
        eloList.clear();
        eloList.addAll(allEloList);
        eloAdapter.notifyDataSetChanged();
    }

    private void setCategoryRecycler(List<tagCategory> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        tagRecycler = findViewById(R.id.tagRecycler);
        tagRecycler.setLayoutManager(layoutManager);
        tagAdapter = new tagAdapter(this, categoryList);
        tagRecycler.setAdapter(tagAdapter);
    }

    private void setEloRecycler(List<Elos> eloList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        eloRecycler = findViewById(R.id.eloRecycler);
        eloRecycler.setLayoutManager(layoutManager);
        eloAdapter = new eloAdapter(this, eloList);
        eloRecycler.setAdapter(eloAdapter);
    }

    public static void showEloByCategory(int category) {

        eloList.clear();
        eloList.addAll(allEloList);

        List<Elos> filter = new ArrayList<>();

        for (Elos c : eloList) {
            if(c.getCategory() == category)
                filter.add(c);
        }

        eloList.clear();
        eloList.addAll(filter);

        eloAdapter.notifyDataSetChanged();
    }
}

