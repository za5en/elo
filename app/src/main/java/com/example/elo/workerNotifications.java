package com.example.elo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.adapter.eloAdapter;
import com.example.elo.adapter.notificationsAdapter;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.Elos;
import com.example.elo.model.Notifications;

import java.util.ArrayList;
import java.util.List;

public class workerNotifications extends AppCompatActivity {
    final Context context = this;
    ImageButton back, settings, search, home, profile;

    RecyclerView nfRecycler;
    static notificationsAdapter nfAdapter;
    static List<Notifications> nfList = new ArrayList<>();
    static List<Notifications> allNfList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nfList.clear();
        nfList.add(new Notifications(1, "Java для Senior",
                "Курс Java\nдля Senior-разработчиков",
                "Курс Java для Senior-разработчиков\nСборник секретиков, недоступных и непонятных обычным девелоперам"));
        nfList.add(new Notifications(2, "Нейросети в Python",
                "Основы машинного обучения\nна Python\n",
                "Основы машинного обучения на Python, создание и обучение нейросетей, алгоритмы работы"));
        nfList.add(new Notifications(3, "Основы Python",
                "Базовые знания Python\nОсновы синтаксиса\n",
                "Базовые знания Python.\nОсновы синтаксиса и другие важные моменты"));
        nfList.add(new Notifications(4, "Front&back",
                "Важные моменты\nсвязи фронта с бэком\n",
                "Важные моменты связи фронта с бэком с точки зрения фронтэндера: как избежать конфликтов"));

        allNfList.addAll(nfList);
        setNfRecycler(nfList);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        profile = findViewById(R.id.profileButton);
        home = findViewById(R.id.homeButton);
        search = findViewById(R.id.searchButton);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, workerProfile.class);
                finish();
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
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

        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setNfRecycler(List<Notifications> nfList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        nfRecycler = findViewById(R.id.eloRecycler);
        nfRecycler.setLayoutManager(layoutManager);
        nfAdapter = new notificationsAdapter(this, nfList);
        nfRecycler.setAdapter(nfAdapter);
    }
}
