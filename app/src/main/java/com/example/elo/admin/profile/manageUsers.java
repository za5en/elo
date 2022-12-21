package com.example.elo.admin.profile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.adapter.userAdapter;
import com.example.elo.admin.adminMain;
import com.example.elo.admin.adminNotifications;
import com.example.elo.admin.adminProfile;
import com.example.elo.admin.adminSearch;
import com.example.elo.model.Users;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class manageUsers extends AppCompatActivity {
    final Context context = this;
    ImageButton back, profile, notifications, home, search;
    RecyclerView userRecycler;
    static userAdapter userAdapter;
    static List<Users> userList = new ArrayList<>();
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String[] columns = {null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_users);

        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

        TextView textView = findViewById(R.id.textView);
        TextPaint paint = textView.getPaint();
        float width = paint.measureText("staff");
        Shader textShader=new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        getColor(R.color.dark),
                        getColor(R.color.middle),
                        getColor(R.color.light),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

        int[] userId = new int[30];
        String[] userType = new String[30];
        String[] userName = new String[30];
        String userSurname = null;
        String[] userEmail = new String[30];
        String[] userPassword = new String[30];
        String[] userLevel = new String[30];
        String[] userTag1 = new String[30];
        String[] userTag2 = new String[30];
        String[] userTag3 = new String[30];
        int k = 0;
        columns = new String[] { "user_id", "user_type", "user_name", "user_surname", "user_email", "user_password", "user_level", "user_tag1", "user_tag2", "user_tag3" };
        Cursor cursor = db.query(DatabaseHelper.DB_USERS, columns, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    userId[k] = cursor.getInt(0);
                    userType[k] = cursor.getString(1);
                    userName[k] = cursor.getString(2);
                    userSurname = cursor.getString(3);
                    userEmail[k] = cursor.getString(4);
                    userPassword[k] = cursor.getString(5);
                    userLevel[k] = cursor.getString(6);
                    userTag1[k] = cursor.getString(7);
                    userTag2[k] = cursor.getString(8);
                    userTag3[k] = cursor.getString(9);
                    userName[k++] += ' ' + userSurname;
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        userList.clear();
        for (int i = 0; i < k; i++) {
            userList.add(new Users(userId[i], userName[i],
                    userLevel[i],
                    userType[i],
                    userEmail[i],
                    userPassword[i],
                    userTag1[i], userTag2[i], userTag3[i]));
        }

        setUserRecycler(userList);

        profile = findViewById(R.id.profileButton);
        notifications = findViewById(R.id.notificationsButton);
        home = findViewById(R.id.homeButton);
        search = findViewById(R.id.searchButton);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, adminProfile.class);
                finish();
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, adminNotifications.class);
                finish();
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

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, adminSearch.class);
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

    private void setUserRecycler(List<Users> userList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        userRecycler = findViewById(R.id.usersRecycler);
        userRecycler.setLayoutManager(layoutManager);
        userAdapter = new userAdapter(this, userList);
        userRecycler.setAdapter(userAdapter);
    }
}
