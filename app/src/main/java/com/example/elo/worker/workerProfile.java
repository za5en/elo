package com.example.elo.worker;

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

import com.example.elo.mentor.MainActivity;
import com.example.elo.R;
import com.example.elo.adapter.eloWorkAdapter;
import com.example.elo.adapter.typeAdapter;
import com.example.elo.admin.adminMain;
import com.example.elo.login.auth;
import com.example.elo.model.EloProfile;
import com.example.elo.model.userTypes;

import java.util.ArrayList;
import java.util.List;

public class workerProfile extends AppCompatActivity {

    final Context context = this;
    ImageButton home, search, notifications, settingsButton;
    int userId, id;
    RecyclerView eloRecycler, typeRecycler;
    typeAdapter typeAdapter;
    static eloWorkAdapter eloAdapter;
    static List<EloProfile> eloList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_profile);

        userId = getIntent().getIntExtra("userId", 15);
        id = getIntent().getIntExtra("id", 0);

        home = findViewById(R.id.homeButton);
        settingsButton = findViewById(R.id.settingsButton);
        search = findViewById(R.id.searchButton);
        notifications = findViewById(R.id.notificationsButton);

        List<userTypes> typesList = new ArrayList<>();
        typesList.add(new userTypes(1, "Сотрудник"));
        typesList.add(new userTypes(2, "Junior"));
        typesList.add(new userTypes(3, "java"));
        typesList.add(new userTypes(4, "sql"));
        typesList.add(new userTypes(5, "python"));

        setTypesRecycler(typesList);

        if (id == 0) {
            eloList.clear();
        }
        else {
            eloList.clear();
            eloList.add(new EloProfile(1, "C# для начинающих",
                    "Курс по C#\nдля начинающих разработчиков\n",
                    "Этот курс поможет освоить C# так, чтобы быть в нём, как рыба в воде, а также подтянуть знания в области ООП",
                    "C# для начинающих", 2, "0%", userId, "c#", "c#", "c#"));
        }
//        eloList.clear();
//        eloList.add(new EloProfile(1, "Java для начинающих",
//                "Курс Java\nдля Junior-разработчиков",
//                "Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java, в первую очередь для работы с сервером",
//                "Java для начинающих", 2, "65%", userId));
//        eloList.add(new EloProfile(2, "C# для начинающих",
//                "Курс по C#\nдля начинающих разработчиков\n",
//                "Этот курс поможет освоить C# так, чтобы быть в нём, как рыба в воде, а также подтянуть знания в области ООП",
//                "C# для начинающих", 2, "65%", userId));
//        eloList.add(new EloProfile(3, "SQL for juniors",
//                "SQL для самых маленьких\nи не только\n",
//                "Азы работы с базами данных, все важные аспекты написания и обработки запросов, особенности работы с PostgreSQL",
//                "SQL for juniors", 6, "65%", userId));
//        eloList.add(new EloProfile(4, "FRONTEND FOR JUNIORS",
//                "база фронтенда\nв одном ЭлО\n",
//                "лучший курс для укрепления основных навыков работы с фронтендом\nплюс вы научитесь связывать фронт с бэком (а это самое главное)",
//                "FRONTEND FOR JUNIORS", 3, "65%", userId));

        setEloRecycler(eloList);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, workerMain.class);
                finish();
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

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, workerNotifications.class);
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
        eloAdapter = new eloWorkAdapter(this, eloList);
        eloRecycler.setAdapter(eloAdapter);
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
//                else if (item.getItemId() == R.id.menu3)
//                {
//                    Intent intent = new Intent(context, MainActivity.class);
//                    startActivity(intent);
//                    return true;
//                }
//                else if (item.getItemId() == R.id.menu4)
//                {
//                    Intent intent = new Intent(context, adminMain.class);
//                    startActivity(intent);
//                    return true;
//                }
                else
                    return false;
            }
        });
        popupMenu.show();
    }
}
