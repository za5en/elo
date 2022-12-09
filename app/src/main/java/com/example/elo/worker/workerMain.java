package com.example.elo.worker;

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

import com.example.elo.R;
import com.example.elo.adapter.eloAdapter;
import com.example.elo.adapter.tagWorkAdapter;
import com.example.elo.model.Elos;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class workerMain extends AppCompatActivity {

    final Context context = this;
    RecyclerView tagRecycler, eloRecycler;
    tagWorkAdapter tagAdapter;
    static eloAdapter eloAdapter;
    static List<Elos> eloList = new ArrayList<>();
    static List<Elos> allEloList = new ArrayList<>();

    ImageButton settings, search, notifications, profile, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_main);

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

        List<tagCategory> first = new ArrayList<>();
        first.add(new tagCategory(1, "java"));
        first.add(new tagCategory(2, "back"));
        first.add(new tagCategory(3, "sql"));
        List<tagCategory> second = new ArrayList<>();
        second.add(new tagCategory(1, "C#"));
        second.add(new tagCategory(2, "back"));
        second.add(new tagCategory(3, "ооп"));
        List<tagCategory> third = new ArrayList<>();
        third.add(new tagCategory(1, "sql"));
        third.add(new tagCategory(2, "базы данных"));
        third.add(new tagCategory(3, "back"));
        List<tagCategory> fourth = new ArrayList<>();
        fourth.add(new tagCategory(1, "front"));
        fourth.add(new tagCategory(2, "java"));
        fourth.add(new tagCategory(3, "react"));

        eloList.clear();
        eloList.add(new Elos(1, "Java для начинающих",
                "Курс Java\nдля Junior-разработчиков",
                "Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backend'ом на Java, в первую очередь для работы с сервером",
                "Java для начинающих", 2, first, true));
        eloList.add(new Elos(2, "C# для начинающих",
                "Курс по C#\nдля начинающих разработчиков\n",
                "Этот курс поможет освоить C# так, чтобы быть в нём, как рыба в воде, а также подтянуть знания в области ООП",
                "C# для начинающих", 2, second, false));
        eloList.add(new Elos(3, "SQL for juniors",
                "SQL для самых маленьких\nи не только\n",
                "Азы работы с базами данных, все важные аспекты написания и обработки запросов, особенности работы с PostgreSQL",
                "SQL for juniors", 7, third, true));
        eloList.add(new Elos(4, "FRONTEND FOR JUNIORS",
                "база фронтенда\nв одном ЭлО\n",
                "лучший курс для укрепления основных навыков работы с фронтендом\nплюс вы научитесь связывать фронт с бэком (а это самое главное)",
                "FRONTEND FOR JUNIORS", 1, fourth, true));

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
                Intent intent = new Intent(context, workerProfile.class);
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
        tagAdapter = new tagWorkAdapter(this, categoryList);
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

