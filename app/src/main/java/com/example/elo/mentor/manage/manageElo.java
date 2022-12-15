package com.example.elo.mentor.manage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.R;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class manageElo extends AppCompatActivity {

    ImageButton back, requests, deleteElo, acceptTask;
    final Context context = this;
    RecyclerView tagRecycler;
    tagAdapter tagAdapter;
    List<tagCategory> categoryList;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_elo);

        requests = findViewById(R.id.requests);
        acceptTask = findViewById(R.id.accept_tasks);
        TextView eloName = findViewById(R.id.elo_name);
        TextView eloDescText = findViewById(R.id.elo_desc_text);

        eloDescText.setText(getIntent().getStringExtra("previewDesc"));
        eloName.setText(getIntent().getStringExtra("previewName"));
        name = eloName.getText().toString();

        List<tagCategory> categoryList = new ArrayList<>();
        if (name.toLowerCase().contains("java")) {
            categoryList.add(new tagCategory(1, "java"));
            categoryList.add(new tagCategory(2, "back"));
            categoryList.add(new tagCategory(3, "sql"));
        }
        else if (name.toLowerCase().contains("python")) {
            categoryList.add(new tagCategory(1, "python"));
            categoryList.add(new tagCategory(2, "back"));
        }
        else if (name.toLowerCase().contains("front&back")) {
            categoryList.add(new tagCategory(1, "front"));
            categoryList.add(new tagCategory(2, "react"));
            categoryList.add(new tagCategory(3, "back"));
        }
        else if (name.toLowerCase().contains("c#")) {
            categoryList.add(new tagCategory(1, "C#"));
            categoryList.add(new tagCategory(2, "back"));
            categoryList.add(new tagCategory(3, "другое"));
        }
        else if (name.toLowerCase().contains("sql")) {
            categoryList.add(new tagCategory(1, "sql"));
            categoryList.add(new tagCategory(2, "другое"));
            categoryList.add(new tagCategory(3, "back"));
        }
        else if (name.toLowerCase().contains("frontend")) {
            categoryList.add(new tagCategory(1, "front"));
            categoryList.add(new tagCategory(2, "java"));
            categoryList.add(new tagCategory(3, "react"));
        }

        setCategoryRecycler(categoryList);

        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.manage.pages.requests.class);
                intent.putExtra("eloName", name);
                startActivity(intent);
            }
        });

        acceptTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.manage.pages.acceptTask.class);
                intent.putExtra("eloName", name);
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

    private void setCategoryRecycler(List<tagCategory> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        tagRecycler = findViewById(R.id.tagRecycler);
        tagRecycler.setLayoutManager(layoutManager);
        tagAdapter = new tagAdapter(this, categoryList);
        tagRecycler.setAdapter(tagAdapter);
    }
}
