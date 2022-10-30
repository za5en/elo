package com.example.elo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.adapter.eloAdapter;
import com.example.elo.adapter.eloProfileAdapter;
import com.example.elo.model.EloProfile;
import com.example.elo.model.Elos;

import java.util.ArrayList;
import java.util.List;

public class profile extends AppCompatActivity {

    final Context context = this;
    ImageButton create, home;
    RecyclerView eloProfileRecycler;
    static eloProfileAdapter eloProfileAdapter;
    static List<EloProfile> eloList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        create = findViewById(R.id.create_elo);
        home = findViewById(R.id.homeButton);

        eloList.clear();
        eloList.add(new EloProfile(1, "Java для начинающих"));
        eloList.add(new EloProfile(2, "Элемент обучения 2"));
        eloList.add(new EloProfile(3, "Элемент обучения 3"));
        eloList.add(new EloProfile(4, "Элемент обучения 4"));

        setEloRecycler(eloList);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, constructor.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setEloRecycler(List<EloProfile> eloList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        eloProfileRecycler = findViewById(R.id.eloProfileRecycler);
        eloProfileRecycler.setLayoutManager(layoutManager);
        eloProfileAdapter = new eloProfileAdapter(this, eloList);
        eloProfileRecycler.setAdapter(eloProfileAdapter);
    }
}
