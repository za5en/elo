package com.example.elo.worker;

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
import com.example.elo.adapter.tagWorkerSearchAdapter;
import com.example.elo.model.Elos;
import com.example.elo.model.tagCategory;
import com.example.elo.worker.workerMain;
import com.example.elo.worker.workerNotifications;
import com.example.elo.worker.workerProfile;

import java.util.ArrayList;
import java.util.List;

public class workerSearch extends AppCompatActivity {
    final Context context = this;
    ImageButton home, notifications, profile, reset;
    RecyclerView tagRecycler, eloRecycler;
    tagWorkerSearchAdapter tagAdapter;
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
        eloList.add(new Elos(1, "Java для начинающих",
                "Курс Java\nдля Junior-разработчиков",
                "Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java, в первую очередь для работы с сервером",
                "Java для начинающих", 2, first, true));
        eloList.add(new Elos(2, "C# для начинающих",
                "Курс по C#\nдля начинающих разработчиков\n",
                "Этот курс поможет освоить C# так, чтобы быть в нём, как рыба в воде, а также подтянуть знания в области ООП",
                "C# для начинающих", 2, second, false));
        eloList.add(new Elos(3, "SQL for juniors",
                "SQL для самых маленьких\nи не только\n",
                "Азы работы с базами данных, все важные аспекты написания и обработки запросов, особенности работы с PostgreSQL",
                "SQL for juniors", 7, third, true));
        eloList.add(new Elos(4, "FRONTEND FOR JUNIORS",
                "база фронтенда\nв одном ЭлО\n",
                "лучший курс для укрепления основных навыков работы с фронтендом\nплюс вы научитесь связывать фронт с бэком (а это самое главное)",
                "FRONTEND FOR JUNIORS", 1, fourth, true));

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
                Intent intent = new Intent(context, workerProfile.class);
                finish();
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, workerNotifications.class);
                finish();
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, workerMain.class);
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
        tagAdapter = new tagWorkerSearchAdapter(this, categoryList);
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
