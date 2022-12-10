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
import com.example.elo.mentor.eloPage.pass.eloPass;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class continueElo extends AppCompatActivity {

    final Context context = this;
    ImageButton back, continue_elo;
    RecyclerView tagRecycler;
    tagAdapter tagAdapter;
    List<tagCategory> categoryList;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continue_elo);

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
            categoryList.add(new tagCategory(3, "ооп"));
        }
        else if (name.toLowerCase().contains("sql")) {
            categoryList.add(new tagCategory(1, "sql"));
            categoryList.add(new tagCategory(2, "БД"));
            categoryList.add(new tagCategory(3, "back"));
        }
        else if (name.toLowerCase().contains("frontend")) {
            categoryList.add(new tagCategory(1, "front"));
            categoryList.add(new tagCategory(2, "java"));
            categoryList.add(new tagCategory(3, "react"));
        }

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
                Intent intent = new Intent(context, eloPass.class);

                intent.putExtra("taskName", "Задание 1");
                intent.putExtra("taskDesc", "прочитать статью:");
                intent.putExtra("url", "https://stackoverflow.com/questions/9643610/how-to-including-variables-within-strings");

                context.startActivity(intent);
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