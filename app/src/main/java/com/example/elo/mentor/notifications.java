package com.example.elo.mentor;

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

public class notifications extends AppCompatActivity {
    final Context context = this;
    ImageButton settings, search, home, profile;

    RecyclerView nfRecycler;
    static notificationsAdapter nfAdapter;
    static List<Notifications> nfList = new ArrayList<>();
    static List<Notifications> allNfList = new ArrayList<>();

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        userId = getIntent().getIntExtra("userId", 2);

        nfList.clear();
        nfList.add(new Notifications(1, "C# для начинающих",
                "Сотрудник выполнил задание",
                "Сотрудник Worker Name выполнил Задание 1 в ЭлО C# для начинающих",
                "20 дек 2022 14:42",
                "Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java, в первую очередь для работы с сервером",
                false, userId));
        nfList.add(new Notifications(2, "Нейросети в Python",
                "Появился новый доступный ЭлО",
                "Появился новый доступный Вам для прохождения ЭлО - Нейросети в Python, необходимый уровень - senior",
                "20 дек 2022 14:42",
                "Основы машинного обучения на Python, создание и обучение нейросетей, алгоритмы работы",
                false, userId));
        nfList.add(new Notifications(3, "Java для начинающих",
                "Новая заявка на вступление",
                "Новая заявка на вступление в Ваш ЭлО Java для начинающих от Worker Name (junior)",
                "20 дек 2022 14:41",
                "Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java, в первую очередь для работы с сервером",
                false, userId));
        nfList.add(new Notifications(4, "Front&back",
                "Уведомление о дедлайне - 24 часа",
                "Осталось 24 часа, чтобы выполнить Задание 3 в ЭлО Front&back",
                "20 дек 2022 14:18",
                "Важные моменты связи фронта с бэком с точки зрения фронтэндера: как избежать конфликтов",
                false, userId));

        allNfList.addAll(nfList);
        setNfRecycler(nfList);

        profile = findViewById(R.id.profileButton);
        home = findViewById(R.id.homeButton);
        search = findViewById(R.id.searchButton);
        settings = findViewById(R.id.settingsButton);

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

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                finish();
                intent.putExtra("userId", userId);
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
                    nfList.add(new Notifications(1, "C# для начинающих",
                            "Сотрудник выполнил задание",
                            "Сотрудник Worker Name выполнил Задание 1 в ЭлО C# для начинающих",
                            "20 дек 2022 14:42",
                            "Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java, в первую очередь для работы с сервером",
                            false, userId));
                    nfList.add(new Notifications(2, "Нейросети в Python",
                            "Появился новый доступный ЭлО",
                            "Появился новый доступный Вам для прохождения ЭлО - Нейросети в Python, необходимый уровень - senior",
                            "20 дек 2022 14:42",
                            "Основы машинного обучения на Python, создание и обучение нейросетей, алгоритмы работы",
                            false, userId));
                    nfList.add(new Notifications(3, "Java для начинающих",
                            "Новая заявка на вступление",
                            "Новая заявка на вступление в Ваш ЭлО Java для начинающих от Worker Name (junior)",
                            "20 дек 2022 14:41",
                            "Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java, в первую очередь для работы с сервером",
                            false, userId));
                    nfList.add(new Notifications(4, "Front&back",
                            "Уведомление о дедлайне - 24 часа",
                            "Осталось 24 часа, чтобы выполнить Задание 3 в ЭлО Front&back",
                            "20 дек 2022 14:18",
                            "Важные моменты связи фронта с бэком с точки зрения фронтэндера: как избежать конфликтов",
                            false, userId));
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
