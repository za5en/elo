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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_elo);

        requests = findViewById(R.id.requests);
        //deleteElo = findViewById(R.id.delete_elo);
        acceptTask = findViewById(R.id.accept_tasks);
        TextView eloName = findViewById(R.id.elo_name);
        TextView eloDescText = findViewById(R.id.elo_desc_text);

        eloDescText.setText(getIntent().getStringExtra("previewDesc"));
        eloName.setText(getIntent().getStringExtra("previewName"));

        List<tagCategory> categoryList = new ArrayList<>();
        categoryList.add(new tagCategory(1, "front"));
        categoryList.add(new tagCategory(2, "back"));
        categoryList.add(new tagCategory(3, "qa"));
        setCategoryRecycler(categoryList);

        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.manage.pages.requests.class);
                startActivity(intent);
            }
        });

        acceptTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.manage.pages.acceptTask.class);
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

//        deleteElo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
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
