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
    String name, taskName, taskDesc, url;

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
        if (name.equals("Java для начинающих")) {
            categoryList.add(new tagCategory(1, "java"));
            categoryList.add(new tagCategory(2, "back"));
            categoryList.add(new tagCategory(3, "sql"));
            taskName = "Задание 1";
            taskDesc = "посмотреть видео:";
            url = "https://www.youtube.com/watch?v=U2OliQwRb6c&list=PLDyJYA6aTY1lT614ixLYq48har7EnCXpk";
        }
        else if (name.equals("Java для Senior")) {
            categoryList.add(new tagCategory(1, "java"));
            categoryList.add(new tagCategory(2, "back"));
            categoryList.add(new tagCategory(3, "sql"));
            taskName = "Задание 3";
            taskDesc = "Immutable Collections. Просмотр видео:";
            url = "https://youtu.be/jMvF2zs7ApA";
        }
        else if (name.equals("Основы Python")) {
            categoryList.add(new tagCategory(1, "python"));
            categoryList.add(new tagCategory(2, "back"));
            taskName = "Задание 2";
            taskDesc = "Изучение 2 главы:";
            url = "https://metanit.com/python/tutorial/2.1.php";
        }
        else if (name.equals("Нейросети в Python")) {
            categoryList.add(new tagCategory(1, "python"));
            categoryList.add(new tagCategory(2, "back"));
            taskName = "Итоговое задание";
            taskDesc = "Просмотр курса по машинному обучению:";
            url = "https://youtu.be/GwIo3gDZCVQ";
        }
        else if (name.toLowerCase().contains("front&back")) {
            categoryList.add(new tagCategory(1, "front"));
            categoryList.add(new tagCategory(2, "react"));
            categoryList.add(new tagCategory(3, "back"));
            taskName = "Задание 1";
            taskDesc = "Ознакомиться с материалом:";
            url = "https://stackoverflow.com/questions/68164444/how-to-connect-backend-and-frontend";
        }
        else if (name.toLowerCase().contains("c#")) {
            categoryList.add(new tagCategory(1, "C#"));
            categoryList.add(new tagCategory(2, "back"));
            categoryList.add(new tagCategory(3, "ооп"));
            taskName = "Задание 3";
            taskDesc = "Изучение 3 главы:";
            url = "https://metanit.com/sharp/tutorial/3.1.php";
        }
        else if (name.toLowerCase().contains("sql")) {
            categoryList.add(new tagCategory(1, "sql"));
            categoryList.add(new tagCategory(2, "БД"));
            categoryList.add(new tagCategory(3, "back"));
            taskName = "Задание 1";
            taskDesc = "Посмотреть видео и попрактиковаться:";
            url = "https://www.youtube.com/watch?v=sLwiFGAOMK4&list=PLqj7-hRTFl_oweCD2cFQYdJDmD5bwEhb9";
        }
        else if (name.toLowerCase().contains("frontend")) {
            categoryList.add(new tagCategory(1, "front"));
            categoryList.add(new tagCategory(2, "java"));
            categoryList.add(new tagCategory(3, "react"));
            taskName = "Задание 4";
            taskDesc = "Посмотреть видео:";
            url = "https://www.youtube.com/watch?v=cBYDQlwGEjQ&list=PLMB6wLyKp7lV9YoWTMCztq-KXYhYPB09K&index=5";
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

                intent.putExtra("taskName", taskName);
                intent.putExtra("taskDesc", taskDesc);
                intent.putExtra("url", url);

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