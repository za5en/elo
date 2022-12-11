package com.example.elo.mentor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

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

        List<tagCategory> first = new ArrayList<>();
        first.add(new tagCategory(1, "java"));
        first.add(new tagCategory(2, "back"));
        first.add(new tagCategory(3, "sql"));
        List<tagCategory> second = new ArrayList<>();
        second.add(new tagCategory(1, "python"));
        second.add(new tagCategory(2, "back"));
        List<tagCategory> third = new ArrayList<>();
        third.add(new tagCategory(1, "c#"));
        third.add(new tagCategory(2, "java"));
        third.add(new tagCategory(3, "front"));
        List<tagCategory> fourth = new ArrayList<>();
        fourth.add(new tagCategory(1, "front"));
        fourth.add(new tagCategory(2, "react"));
        fourth.add(new tagCategory(3, "back"));

        eloList.clear();
        allEloList.clear();
        eloList.add(new Elos(1, "Java для Senior",
                "Курс Java\nдля Senior-разработчиков",
                "Курс Java для Senior-разработчиков\nСборник секретиков, недоступных и непонятных обычным девелоперам",
                "Java для Senior", 4, first, false));
        eloList.add(new Elos(2, "Нейросети в Python",
                "Основы машинного обучения\nна Python\n",
                "Основы машинного обучения на Python, создание и обучение нейросетей, алгоритмы работы",
                "Нейросети в Python", 5, second, false));
        eloList.add(new Elos(3, "Основы Python",
                "Базовые знания Python\nОсновы синтаксиса\n",
                "Базовые знания Python.\nОсновы синтаксиса и другие важные моменты",
                "Основы Python", 5, second, true));
        eloList.add(new Elos(4, "Front&back",
                "Важные моменты\nсвязи фронта с бэком\n",
                "Важные моменты связи фронта с бэком с точки зрения фронтэндера: как избежать конфликтов",
                "Front&back", 1, fourth, false));

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
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.notifications.class);
                finish();
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                finish();
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
