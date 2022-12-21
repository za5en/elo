package com.example.elo.worker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.DatabaseHelper;
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
    RecyclerView eloRecycler, typeRecycler;
    typeAdapter typeAdapter;
    static eloWorkAdapter eloAdapter;
    static List<EloProfile> eloList = new ArrayList<>();

    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String[] columns = null;
    String selection = null;
    String[] selectionArgs = null;

    TextView username;

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_profile);

        userId = getIntent().getIntExtra("userId", 15);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

        home = findViewById(R.id.homeButton);
        settingsButton = findViewById(R.id.settingsButton);
        search = findViewById(R.id.searchButton);
        notifications = findViewById(R.id.notificationsButton);

        String userName = null;
        String userSurname = null;
        String userType = null;
        String userLevel = null;
        String userTag1 = null;
        String userTag2 = null;
        String userTag3 = null;

        int[] eloId = new int[20];
        String[] eloName = new String[20];
        String[] eloShortInfo = new String[20];
        String[] eloInfo = new String[20];
        String[] eloTag1 = new String[20];
        String[] eloTag2 = new String[20];
        String[] eloTag3 = new String[20];
        String userAmount = null, wholeAmount = null;

        //add user info
        columns = new String[] { "user_type", "user_name", "user_surname", "user_level", "user_tag1", "user_tag2", "user_tag3" };
        selection = "user_id = ?";
        selectionArgs = new String[] { String.valueOf(userId) };
        Cursor cursor = db.query(DatabaseHelper.DB_USERS, columns, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userType = cursor.getString(0);
                userName = cursor.getString(1);
                userSurname = cursor.getString(2);
                userLevel = cursor.getString(3);
                userTag1 = cursor.getString(4);
                userTag2 = cursor.getString(5);
                userTag3 = cursor.getString(6);
            }
        }
        cursor.close();
        userName += ' ' + userSurname;
        username = findViewById(R.id.username);
        username.setText(userName);

        List<userTypes> typesList = new ArrayList<>();
        typesList.add(new userTypes(1, userType));
        typesList.add(new userTypes(2, userLevel));
        typesList.add(new userTypes(3, userTag1));
        typesList.add(new userTypes(4, userTag2));
        typesList.add(new userTypes(5, userTag3));

        setTypesRecycler(typesList);

        //add elos
        int j = 0;
        columns = new String[] { "elo_id" };
        selection = "user_id = ?";
        selectionArgs = new String[] { String.valueOf(userId) };
        cursor = db.query(DatabaseHelper.DB_REL_LIST, columns, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    eloId[j++] = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();


        columns = new String[] { "elo_name", "elo_short_info", "elo_info", "elo_tag1", "elo_tag2", "elo_tag3" };
        selection = "elo_id = ?";
        for (int k = 0; k < j; k++) {
            selectionArgs = new String[] { String.valueOf(eloId[k]) };
            cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        eloName[k] = cursor.getString(0);
                        eloShortInfo[k] = cursor.getString(1);
                        eloInfo[k] = cursor.getString(2);
                        eloTag1[k] = cursor.getString(3);
                        eloTag2[k] = cursor.getString(4);
                        eloTag3[k] = cursor.getString(5);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        }

        eloList.clear();
        for (int k = 0; k < j; k++) {
            String task_count = "select count(*) from elo_tasks where elo_id = '" + eloId[k] + "';";
            cursor = db.rawQuery(task_count, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    wholeAmount = cursor.getString(0);
                }
                cursor.close();
            }

            task_count = "select task_id from task_progress where user_id = '"+ userId +"' and elo_id = '"+ eloId[k] +"';";
            cursor = db.rawQuery(task_count, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    userAmount = cursor.getString(0);
                }
                cursor.close();
            }
            eloList.add(new EloProfile(eloId[k], eloName[k],
                    eloShortInfo[k],
                    eloInfo[k],
                    eloName[k], 2, userAmount + "/" + wholeAmount, userId,
                    eloTag1[k], eloTag2[k], eloTag3[k]));
        }

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
