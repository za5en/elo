package com.example.elo.mentor.manage.pages;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.adapter.acceptTaskAdapter;
import com.example.elo.adapter.eloAdapter;
import com.example.elo.adapter.requestsAdapter;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.AcceptTask;
import com.example.elo.model.Elos;
import com.example.elo.model.Request;

import java.util.ArrayList;
import java.util.List;

public class requests extends AppCompatActivity {
    RecyclerView requestsRecycler;
    static requestsAdapter requestsAdapter;
    final Context context = this;

    static List<Request> users = new ArrayList<>();
    static List<Request> allUsers = new ArrayList<>();

    String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests);
        ImageButton back = findViewById(R.id.backButton);

        name = getIntent().getStringExtra("eloName");

        TextView textView = findViewById(R.id.textView);
        TextPaint paint = textView.getPaint();
        float width = paint.measureText("tasks");
        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        getColor(R.color.dark),
                        getColor(R.color.middle),
                        getColor(R.color.light),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        users.clear();
        allUsers.clear();


        if (name.toLowerCase().contains("java")) {
            users.add(new Request("Максим Максим","back junior", 1));
            users.add(new Request("Кирилл Широбоков","back junior", 2));
            users.add(new Request("Самвел Семенов","front junior", 3));
            users.add(new Request("Worker Name","front junior", 4));
        }
        else if (name.toLowerCase().contains("python")) {
            users.add(new Request("Михаил Михайлович","back senior", 1));
            users.add(new Request("Самвел Семенов","front junior", 2));
        }
        else if (name.toLowerCase().contains("front&back")) {
            users.add(new Request("Виталий Новый","back senior", 1));
            users.add(new Request("Артём Коновалов","back middle", 2));
            users.add(new Request("Дима Перевозчиков","front middle", 3));
            users.add(new Request("Самвел Семенов","front junior", 4));
        }
        else if (name.toLowerCase().contains("c#")) {
            users.add(new Request("Фёдор Власов","front senior", 1));
            users.add(new Request("Кирилл Широбоков","back junior", 2));
            users.add(new Request("Самвел Семенов","front junior", 3));
        }
        else if (name.toLowerCase().contains("sql")) {
            users.add(new Request("Фёдор Власов","front senior", 1));
            users.add(new Request("Кирилл Широбоков","back junior", 2));
            users.add(new Request("Самвел Семенов","front junior", 3));
            users.add(new Request("Alex Kiselev","front junior", 4));
            users.add(new Request("Worker Name","front junior", 5));
        }
        else if (name.toLowerCase().contains("frontend")) {
            users.add(new Request("RUSTAM GPOWER","back junior", 1));
            users.add(new Request("Alex Kiselev","front junior", 2));
            users.add(new Request("Самвел Семенов","front junior", 3));
            users.add(new Request("Богдан Бельский","front junior", 4));
            users.add(new Request("Worker Name","front junior", 5));
        }

        allUsers.addAll(users);
        setRequestRecycler(users);
    }

    private void setRequestRecycler(List<Request> users) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        requestsRecycler = findViewById(R.id.requestsRecycler);
        requestsRecycler.setLayoutManager(layoutManager);
        requestsAdapter = new requestsAdapter(this, users);
        requestsRecycler.setAdapter(requestsAdapter);
    }

    public static void removeTasks(int id) {

        users.clear();
        users.addAll(allUsers);

        List<Request> filter = new ArrayList<>();

        for (Request c : users) {
            if(c.getId() != id)
                filter.add(c);
        }

        users.clear();
        users.addAll(filter);
        allUsers.clear();
        allUsers.addAll(filter);

        requestsAdapter.notifyDataSetChanged();
    }
}