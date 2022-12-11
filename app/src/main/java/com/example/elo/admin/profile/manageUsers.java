package com.example.elo.admin.profile;

import android.content.Context;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_users);

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

        userList.clear();
        userList.add(new Users(1, "Mentor name",
                "senior",
                "наставник",
                "mail1@mail.ru",
                "mentSj17aks", first));
        userList.add(new Users(2, "Worker name",
                "junior",
                "сотрудник",
                "mail2@mail.ru",
                "emplPj225qlzv", third));
        userList.add(new Users(3, "Рустам Авангард",
                "senior",
                "наставник",
                "mail3@mail.ru",
                "111111111", second));
        userList.add(new Users(4, "Федор Власов",
                "senior",
                "наставник",
                "mail4@mail.ru",
                "qska1jmsandc_", fourth));

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
