package com.example.elo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

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

public class adminNotifications extends AppCompatActivity {
    final Context context = this;
    ImageButton back, settings, search, home, profile;

    RecyclerView nfRecycler;
    static notificationsAdapter nfAdapter;
    static List<Notifications> nfList = new ArrayList<>();
    static List<Notifications> allNfList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        nfList.clear();
        nfList.add(new Notifications(1, "Java для Senior",
                "Курс Java\nдля Senior-разработчиков",
                "Курс Java для Senior-разработчиков\nСборник секретиков, недоступных и непонятных обычным девелоперам",
                false));
        nfList.add(new Notifications(2, "Нейросети в Python",
                "Основы машинного обучения\nна Python\n",
                "Основы машинного обучения на Python, создание и обучение нейросетей, алгоритмы работы",
                false));
        nfList.add(new Notifications(3, "Основы Python",
                "Базовые знания Python\nОсновы синтаксиса\n",
                "Базовые знания Python.\nОсновы синтаксиса и другие важные моменты",
                false));
        nfList.add(new Notifications(4, "Front&back",
                "Важные моменты\nсвязи фронта с бэком\n",
                "Важные моменты связи фронта с бэком с точки зрения фронтэндера: как избежать конфликтов",
                false));

        allNfList.addAll(nfList);
        setNfRecycler(nfList);

        profile = findViewById(R.id.profileButton);
        home = findViewById(R.id.homeButton);
        search = findViewById(R.id.searchButton);
        settings = findViewById(R.id.settingsButton);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, adminProfile.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, adminSearch.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, adminMain.class);
                finish();
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });
    }

    private void setNfRecycler(List<Notifications> nfList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        nfRecycler = findViewById(R.id.nfRecycler);
        nfRecycler.setLayoutManager(layoutManager);
        nfAdapter = new notificationsAdapter(this, nfList);
        nfRecycler.setAdapter(nfAdapter);
    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popupmenu_nf);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu1)
                {
                    nfList.clear();
                    nfAdapter.notifyDataSetChanged();
                    return true;
                }
                else if (item.getItemId() == R.id.menu2)
                {
                    nfList.clear();
                    nfList.add(new Notifications(1, "Java для Senior",
                            "Курс Java\nдля Senior-разработчиков",
                            "Курс Java для Senior-разработчиков\nСборник секретиков, недоступных и непонятных обычным девелоперам",
                            true));
                    nfList.add(new Notifications(2, "Нейросети в Python",
                            "Основы машинного обучения\nна Python\n",
                            "Основы машинного обучения на Python, создание и обучение нейросетей, алгоритмы работы",
                            true));
                    nfList.add(new Notifications(3, "Основы Python",
                            "Базовые знания Python\nОсновы синтаксиса\n",
                            "Базовые знания Python.\nОсновы синтаксиса и другие важные моменты",
                            true));
                    nfList.add(new Notifications(4, "Front&back",
                            "Важные моменты\nсвязи фронта с бэком\n",
                            "Важные моменты связи фронта с бэком с точки зрения фронтэндера: как избежать конфликтов",
                            true));
                    nfAdapter.notifyDataSetChanged();
                    return true;
                }
                else
                    return false;
            }
        });
        popupMenu.show();
    }
}
