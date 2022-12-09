package com.example.elo.admin.profile.manage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.R;
import com.example.elo.adapter.adminEloWorkAdapter;
import com.example.elo.adapter.deleteIconsAdapter;
import com.example.elo.adapter.deleteIconsWorkAdapter;
import com.example.elo.model.DeleteIcons;
import com.example.elo.model.EloProfile;

import java.util.ArrayList;
import java.util.List;

public class eloPart extends AppCompatActivity {
    final Context context = this;
    RecyclerView eloWorkerRecycler, iconsRecycler;
    static deleteIconsWorkAdapter deleteIconsAdapter;
    static adminEloWorkAdapter eloWorkerAdapter;
    static List<EloProfile> eloList = new ArrayList<>();
    static List<EloProfile> allEloList = new ArrayList<>();
    static List<DeleteIcons> iconsList = new ArrayList<>();

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elo_part);

        eloList.clear();
        eloList.add(new EloProfile(1, "Java для Senior",
                "Курс Java\nдля Senior-разработчиков",
                "Курс Java для Senior-разработчиков\nСборник секретиков, недоступных и непонятных обычным девелоперам",
                "Java для Senior", 4, "65%"));
        eloList.add(new EloProfile(2, "Нейросети в Python",
                "Основы машинного обучения\nна Python\n",
                "Основы машинного обучения на Python, создание и обучение нейросетей, алгоритмы работы",
                "Нейросети в Python", 5, "65%"));
        eloList.add(new EloProfile(3, "Основы Python",
                "Базовые знания Python\nОсновы синтаксиса\n",
                "Базовые знания Python.\nОсновы синтаксиса и другие важные моменты",
                "Основы Python", 5, "65%"));
        eloList.add(new EloProfile(4, "Front&back",
                "Важные моменты\nсвязи фронта с бэком\n",
                "Важные моменты связи фронта с бэком с точки зрения фронтэндера: как избежать конфликтов",
                "Front&back", 1, "65%"));

        allEloList.addAll(eloList);
        setEloWorkerRecycler(eloList);

        iconsList.clear();
        iconsList.add(new DeleteIcons(1));
        iconsList.add(new DeleteIcons(2));
        iconsList.add(new DeleteIcons(3));
        iconsList.add(new DeleteIcons(4));

        setIconsRecycler(iconsList);

        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setEloWorkerRecycler(List<EloProfile> eloList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        eloWorkerRecycler = findViewById(R.id.eloRecycler);
        eloWorkerRecycler.setHasFixedSize(true);
        eloWorkerRecycler.setNestedScrollingEnabled(false);
        eloWorkerRecycler.setLayoutManager(layoutManager);
        eloWorkerAdapter = new adminEloWorkAdapter(this, eloList);
        eloWorkerRecycler.setAdapter(eloWorkerAdapter);
    }

    private void setIconsRecycler(List<DeleteIcons> iconsList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        iconsRecycler = findViewById(R.id.deleteRecycler);
        iconsRecycler.setHasFixedSize(true);
        iconsRecycler.setNestedScrollingEnabled(false);
        iconsRecycler.setLayoutManager(layoutManager);
        deleteIconsAdapter = new deleteIconsWorkAdapter(this, iconsList);
        iconsRecycler.setAdapter(deleteIconsAdapter);
    }

    public static void removeElo(int id) {

        List<EloProfile> filter = new ArrayList<>();
        List<DeleteIcons> filter_del = new ArrayList<>();

        for (EloProfile c : eloList) {
            if(c.getId() != id)
                filter.add(c);
        }
        for (DeleteIcons c : iconsList) {
            if (c.getId() != id)
                filter_del.add(c);
        }

        eloList.clear();
        eloList.addAll(filter);
        iconsList.clear();
        iconsList.addAll(filter_del);

        eloWorkerAdapter.notifyDataSetChanged();
        deleteIconsAdapter.notifyDataSetChanged();
    }
}
