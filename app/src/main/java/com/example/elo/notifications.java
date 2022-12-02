package com.example.elo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.adapter.eloAdapter;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.Elos;

import java.util.ArrayList;
import java.util.List;

public class notifications extends AppCompatActivity {
    final Context context = this;
    ImageButton settings, search, home, profile;

    RecyclerView nfRecycler;
    com.example.elo.adapter.tagAdapter tagAdapter;
    static com.example.elo.adapter.eloAdapter eloAdapter;
    static List<Elos> eloList = new ArrayList<>();
    static List<Elos> allEloList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //notifications for admins and workers

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        profile = findViewById(R.id.profileButton);
        home = findViewById(R.id.homeButton);
        search = findViewById(R.id.searchButton);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, profile.class);
                finish();
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, search.class);
                finish();
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
