package com.example.elo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class adminSearch extends AppCompatActivity {
    final Context context = this;
    ImageButton settings, home, notifications, profile, reset;
    RecyclerView tagRecycler;
    tagAdapter tagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        List<tagCategory> categoryList = new ArrayList<>();
        categoryList.add(new tagCategory(1, "front"));
        categoryList.add(new tagCategory(2, "back"));
        categoryList.add(new tagCategory(3, "qa"));
        categoryList.add(new tagCategory(4, "java"));
        categoryList.add(new tagCategory(5, "python"));
        categoryList.add(new tagCategory(6, "c#"));
        categoryList.add(new tagCategory(7, "sql"));
        categoryList.add(new tagCategory(8, "react"));
        categoryList.add(new tagCategory(9, "другое"));
        categoryList.add(new tagCategory(10, "debug"));

        profile = findViewById(R.id.profileButton);
        notifications = findViewById(R.id.notificationsButton);
        home = findViewById(R.id.homeButton);

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



//        reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resetEloCategories(view);
//            }
//        });
    }

    private void setCategoryRecycler(List<tagCategory> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        tagRecycler = findViewById(R.id.tagRecycler);
        tagRecycler.setLayoutManager(layoutManager);
        tagAdapter = new tagAdapter(this, categoryList);
        tagRecycler.setAdapter(tagAdapter);
    }
}
