package com.example.elo.mentor;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.adapter.eloAdapter;
import com.example.elo.adapter.tagSearchAdapter;
import com.example.elo.model.Elos;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {
    final Context context = this;
    ImageButton home, notifications, profile, reset;
    RecyclerView tagRecycler, eloRecycler;
    tagSearchAdapter tagAdapter;
    SearchView searchView;
    static eloAdapter eloAdapter;
    static List<Elos> eloList = new ArrayList<>();
    static List<Elos> allEloList = new ArrayList<>();
    List<Elos> filter = new ArrayList<>();

    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String[] columns = {null};

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        userId = getIntent().getIntExtra("userId", 2);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

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
                if (i == 0 || i == 2 || i == 3 || i == 5 || i > 7) {
                    eloList.add(new Elos(eloId[i], eloName[i],
                            eloShortInfo[i],
                            eloInfo[i],
                            eloName[i], i + 1, false, userId,
                            eloTag1[i], eloTag2[i], eloTag3[i]));
                }
            }
        }

        allEloList.clear();
        allEloList.addAll(eloList);
        setEloRecycler(eloList);

        profile = findViewById(R.id.profileButton);
        notifications = findViewById(R.id.notificationsButton);
        home = findViewById(R.id.homeButton);
        reset = findViewById(R.id.resetButton);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (filter.isEmpty())
                    Toast.makeText(context, "ничего не найдено", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                eloList.clear();
                eloAdapter.setFilteredList(eloList);
                return false;
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.profile.class);
                finish();
                userId = getIntent().getIntExtra("userId", 2);
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.notifications.class);
                finish();
                userId = getIntent().getIntExtra("userId", 2);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                finish();
                userId = getIntent().getIntExtra("userId", 2);
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

    private void filterList(String text) {
        filter.clear();
        for (Elos item : allEloList){
            if (item.getName().toLowerCase().contains(text.toLowerCase()) && !(text.isEmpty())) {
                filter.add(item);
            }
        }

        if (filter.isEmpty()) {
            filter.clear();
            eloAdapter.setFilteredList(filter);
        }
        else {
            eloAdapter.setFilteredList(filter);
        }
    }

    public void resetEloCategories(View view) {
        eloList.clear();
        eloAdapter.setFilteredList(eloList);
        eloAdapter.notifyDataSetChanged();
    }

    private void setCategoryRecycler(List<tagCategory> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        tagRecycler = findViewById(R.id.tagRecycler);
        tagRecycler.setLayoutManager(layoutManager);
        tagAdapter = new tagSearchAdapter(this, categoryList);
        tagRecycler.setAdapter(tagAdapter);
    }

    private void setEloRecycler(List<Elos> eloList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        eloRecycler = findViewById(R.id.eloRecycler);
        eloRecycler.setLayoutManager(layoutManager);
        eloList.clear();
        eloAdapter = new eloAdapter(this, eloList);
        eloRecycler.setAdapter(eloAdapter);
    }

    public static void showEloByCategory(int category) {

        eloList.clear();
        eloList.addAll(allEloList);

        List<Elos> filter = new ArrayList<>();
        filter.clear();

        for (Elos c : eloList) {
            if(c.getCategory() == category)
                filter.add(c);
        }

        eloList.clear();
        eloList.addAll(filter);

        eloAdapter.notifyDataSetChanged();
    }
}
