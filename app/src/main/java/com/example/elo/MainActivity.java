package com.example.elo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.elo.adapter.eloAdapter;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.Elos;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    RecyclerView tagRecycler, eloRecycler;
    tagAdapter tagAdapter;
    static eloAdapter eloAdapter;
    static List<Elos> eloList = new ArrayList<>();
    static List<Elos> allEloList = new ArrayList<>();

    ImageButton settings, search, notifications, profile, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.elo);
        TextPaint paint = textView.getPaint();
        float width = paint.measureText("elo");
        Shader textShader=new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        getColor(R.color.dark),
                        getColor(R.color.middle),
                        getColor(R.color.light),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

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

        setCategoryRecycler(categoryList);

        eloList.clear();
        eloList.add(new Elos(1, "Java для начинающих",
                "Курс Java\nдля Junior-разработчиков",
                "Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java, в первую очередь для работы с сервером",
                "Java для начинающих", 1));
        eloList.add(new Elos(2, "Элемент обучения 2",
                "Элемент обучения\nЭлемент обучения\n",
                "Элемент обучения\nЭлемент обучения\nЭлемент обучения\n",
                "Элемент обучения 2", 1));
        eloList.add(new Elos(3, "Элемент обучения 3",
                "Элемент обучения\nЭлемент обучения\n",
                "Элемент обучения\nЭлемент обучения\nЭлемент обучения\nЭлемент обучения",
                "Элемент обучения 3", 2));
        eloList.add(new Elos(4, "Элемент обучения 4",
                "Элемент обучения\nЭлемент обучения\n",
                "Элемент обучения\n\nЭлемент обучения\nЭлемент обучения\nЭлемент обучения\n",
                "Элемент обучения 4", 3));

        allEloList.addAll(eloList);
        setEloRecycler(eloList);

        settings = findViewById(R.id.settingsButton);
        search = findViewById(R.id.searchButton);
        notifications = findViewById(R.id.notificationsButton);
        profile = findViewById(R.id.profileButton);
        reset = findViewById(R.id.resetButton);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, profile.class);
                startActivity(intent);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetEloCategories(view);
            }
        });
    }

    public void resetEloCategories(View view) {
        eloList.clear();
        eloList.addAll(allEloList);
        eloAdapter.notifyDataSetChanged();
    }

    private void setCategoryRecycler(List<tagCategory> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        tagRecycler = findViewById(R.id.tagRecycler);
        tagRecycler.setLayoutManager(layoutManager);
        tagAdapter = new tagAdapter(this, categoryList);
        tagRecycler.setAdapter(tagAdapter);
    }

    private void setEloRecycler(List<Elos> eloList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        eloRecycler = findViewById(R.id.eloRecycler);
        eloRecycler.setLayoutManager(layoutManager);
        eloAdapter = new eloAdapter(this, eloList);
        eloRecycler.setAdapter(eloAdapter);
    }

    public static void showEloByCategory(int category) {

        eloList.clear();
        eloList.addAll(allEloList);

        List<Elos> filter = new ArrayList<>();

        for (Elos c : eloList) {
            if(c.getCategory() == category)
                filter.add(c);
        }

        eloList.clear();
        eloList.addAll(filter);

        eloAdapter.notifyDataSetChanged();
    }
}

