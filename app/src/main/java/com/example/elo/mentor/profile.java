package com.example.elo.mentor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.R;
import com.example.elo.adapter.eloProfileAdapter;
import com.example.elo.adapter.eloWorkAdapter;
import com.example.elo.adapter.typeAdapter;
import com.example.elo.admin.adminMain;
import com.example.elo.login.auth;
import com.example.elo.model.EloProfile;
import com.example.elo.model.userTypes;
import com.example.elo.worker.workerMain;

import java.util.ArrayList;
import java.util.List;

public class profile extends AppCompatActivity {

    final Context context = this;
    ImageButton create, home, search, notifications, settingsButton;
    RecyclerView eloRecycler, eloWorkerRecycler, typeRecycler;
    typeAdapter typeAdapter;
    static eloProfileAdapter eloAdapter;
    static List<EloProfile> eloList = new ArrayList<>();
    static eloWorkAdapter eloWorkerAdapter;
    static List<EloProfile> eloWorkerList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        create = findViewById(R.id.create_elo);
        home = findViewById(R.id.homeButton);
        settingsButton = findViewById(R.id.settingsButton);
        search = findViewById(R.id.searchButton);
        notifications = findViewById(R.id.notificationsButton);

        List<userTypes> typesList = new ArrayList<>();
        typesList.add(new userTypes(1, "Наставник"));
        typesList.add(new userTypes(2, "Senior"));
        typesList.add(new userTypes(3, "C#"));
        typesList.add(new userTypes(4, "java"));
        typesList.add(new userTypes(5, "sql"));

        setTypesRecycler(typesList);

        eloList.clear();
        eloList.add(new EloProfile(1, "Java для начинающих",
                "Курс Java\nдля Junior-разработчиков",
                "Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java, в первую очередь для работы с сервером",
                "Java для начинающих", 2, "65%"));
        eloList.add(new EloProfile(2, "C# для начинающих",
                "Курс по C#\nдля начинающих разработчиков\n",
                "Этот курс поможет освоить C# так, чтобы быть в нём, как рыба в воде, а также подтянуть знания в области ООП",
                "C# для начинающих", 2, "65%"));
        eloList.add(new EloProfile(3, "SQL for juniors",
                "SQL для самых маленьких\nи не только\n",
                "Азы работы с базами данных, все важные аспекты написания и обработки запросов, особенности работы с PostgreSQL",
                "SQL for juniors", 6, "65%"));

        setEloRecycler(eloList);

        eloWorkerList.clear();
        eloWorkerList.add(new EloProfile(1, "Java для Senior",
                "Курс Java\nдля Senior-разработчиков",
                "Курс Java для Senior-разработчиков\nСборник секретиков, недоступных и непонятных обычным девелоперам",
                "Java для Senior", 4, "65%"));
        eloWorkerList.add(new EloProfile(2, "Нейросети в Python",
                "Основы машинного обучения\nна Python\n",
                "Основы машинного обучения на Python, создание и обучение нейросетей, алгоритмы работы",
                "Нейросети в Python", 5, "65%"));
        eloWorkerList.add(new EloProfile(3, "Основы Python",
                "Базовые знания Python\nОсновы синтаксиса\n",
                "Базовые знания Python.\nОсновы синтаксиса и другие важные моменты",
                "Основы Python", 5, "65%"));
        eloWorkerList.add(new EloProfile(4, "Front&back",
                "Важные моменты\nсвязи фронта с бэком\n",
                "Важные моменты связи фронта с бэком с точки зрения фронтэндера: как избежать конфликтов",
                "Front&back", 1, "65%"));

        setEloWorkerRecycler(eloWorkerList);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, constructor.class);
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

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.search.class);
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.notifications.class);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });
    }

    private void setEloRecycler(List<EloProfile> eloList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        eloRecycler = findViewById(R.id.eloProfileRecycler);
        eloRecycler.setLayoutManager(layoutManager);
        eloAdapter = new eloProfileAdapter(this, eloList);
        eloRecycler.setAdapter(eloAdapter);
    }

    private void setEloWorkerRecycler(List<EloProfile> eloList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        eloWorkerRecycler = findViewById(R.id.eloWorkerRecycler);
        eloWorkerRecycler.setLayoutManager(layoutManager);
        eloWorkerAdapter = new eloWorkAdapter(this, eloList);
        eloWorkerRecycler.setAdapter(eloWorkerAdapter);
    }

    private void setTypesRecycler(List<userTypes> typesList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        typeRecycler = findViewById(R.id.user_tags);
        typeRecycler.setLayoutManager(layoutManager);
        typeAdapter = new typeAdapter(this, typesList);
        typeRecycler.setAdapter(typeAdapter);
    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popupmenu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.menu1)
                        {
                            Intent intent = new Intent(context, auth.class);
                            finish();
                            finish();
                            startActivity(intent);
                            return true;
                        }
                        else if (item.getItemId() == R.id.menu2)
                        {
                            LayoutInflater layoutInflater = LayoutInflater.from(context);
                            View dialView = layoutInflater.inflate(R.layout.nf_deadline_dial, null);
                            AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                            dialBuilder.setView(dialView);

                            dialBuilder.setCancelable(false)
                                    .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    })
                                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });

                            dialBuilder.create().show();
                            return true;
                        }
                        else if (item.getItemId() == R.id.menu3)
                        {
                            Intent intent = new Intent(context, workerMain.class);
                            startActivity(intent);
                            return true;
                        }
                        else if (item.getItemId() == R.id.menu4)
                        {
                            Intent intent = new Intent(context, adminMain.class);
                            startActivity(intent);
                            return true;
                        }
                        else
                            return false;
                    }
                });
        popupMenu.show();
    }
}
