package com.example.elo.worker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.R;
import com.example.elo.adapter.notificationsAdapter;
import com.example.elo.model.Notifications;

import java.util.ArrayList;
import java.util.List;

public class workerNotifications extends AppCompatActivity {
    final Context context = this;
    ImageButton settings, search, home, profile;

    RecyclerView nfRecycler;
    static notificationsAdapter nfAdapter;
    static List<Notifications> nfList = new ArrayList<>();
    static List<Notifications> allNfList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);
        nfList.clear();
        nfList.add(new Notifications(1, "C# для начинающих",
                "Выполнение задания подтверждено",
                "Выполнение Задания 5 было подтверждено наставником",
                false));
        nfList.add(new Notifications(2, "Java для начинающих. Часть 2",
                "Появился новый доступный ЭлО",
                "Появился новый доступный Вам для прохождения ЭлО - Java для начинающих. Часть 2, необходимый уровень - junior",
                false));
        nfList.add(new Notifications(3, "SQL for juniors",
                "Принята заявка на вступление",
                "Ваша заявка на вступление ЭлО SQL for juniors была принята, теперь Вы можете выполнять задания",
                false));
        nfList.add(new Notifications(4, "FRONTEND FOR JUNIORS",
                "Уведомление о дедлайне - 24 часа",
                "Осталось 24 часа, чтобы выполнить Задание 3 в ЭлО Front&back",
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
                Intent intent = new Intent(context, workerProfile.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, workerSearch.class);
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
