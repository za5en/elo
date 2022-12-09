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

public class EloInfo extends AppCompatActivity {

    final Context context = this;
    ImageButton back, take_part;
    RecyclerView tagRecycler;
    tagAdapter tagAdapter;
    List<tagCategory> categoryList;
    boolean isPrivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elo_info);

        TextView eloName = findViewById(R.id.elo_name);
        TextView eloDescText = findViewById(R.id.elo_desc_text);

        eloDescText.setText(getIntent().getStringExtra("previewDesc"));
        eloName.setText(getIntent().getStringExtra("previewName"));
        isPrivate = getIntent().getBooleanExtra("isPrivate", true);
        take_part = findViewById(R.id.take_part);

        if (isPrivate)
        {
            take_part.setImageResource(R.drawable.request);
        }

        List<tagCategory> categoryList = new ArrayList<>();
        categoryList.add(new tagCategory(1, "front"));
        categoryList.add(new tagCategory(2, "back"));
        categoryList.add(new tagCategory(3, "qa"));

        setCategoryRecycler(categoryList);

        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        take_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                take_part.setEnabled(false);
                if (!isPrivate) {
                    Toast.makeText(context, "ЭлО добавлен", Toast.LENGTH_SHORT).show();
                    take_part.setImageResource(R.drawable.takepartunavailable);
                }
                else {
                    Toast.makeText(context, "Заявка подана", Toast.LENGTH_SHORT).show();
                    take_part.setImageResource(R.drawable.request_off);
                }
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