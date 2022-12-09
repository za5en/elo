package com.example.elo.mentor.eloPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.R;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class continueElo extends AppCompatActivity {

    final Context context = this;
    ImageButton back, continue_elo;
    RecyclerView tagRecycler;
    tagAdapter tagAdapter;
    List<tagCategory> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continue_elo);

        TextView eloName = findViewById(R.id.elo_name);
        TextView eloDescText = findViewById(R.id.elo_desc_text);

        eloDescText.setText(getIntent().getStringExtra("previewDesc"));
        eloName.setText(getIntent().getStringExtra("previewName"));

        List<tagCategory> categoryList = new ArrayList<>();
        categoryList.add(new tagCategory(1, "front"));
        categoryList.add(new tagCategory(2, "back"));
        categoryList.add(new tagCategory(3, "qa"));

        setCategoryRecycler(categoryList);

        continue_elo = findViewById(R.id.continueElo);

        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        continue_elo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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