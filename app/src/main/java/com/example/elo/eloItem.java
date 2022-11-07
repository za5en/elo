package com.example.elo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.adapter.eloItemAdapter;
import com.example.elo.adapter.tagAdapter;
import com.example.elo.model.eloItems;
import com.example.elo.model.tagCategory;

import java.util.ArrayList;
import java.util.List;

public class eloItem  extends AppCompatActivity {
    
    eloItemAdapter eloItemAdapter;
    RecyclerView tagRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elo_item);

        List<eloItems> tagList = new ArrayList<>();
        tagList.add(new eloItems(1, "front"));
        tagList.add(new eloItems(2, "back"));
        tagList.add(new eloItems(3, "qa"));
        tagList.add(new eloItems(4, "java"));
        tagList.add(new eloItems(5, "python"));
        tagList.add(new eloItems(6, "c#"));
        tagList.add(new eloItems(7, "sql"));
        tagList.add(new eloItems(8, "react"));
        tagList.add(new eloItems(9, "другое"));
        tagList.add(new eloItems(10, "debug"));

        //setTagRecycler(tagList);
    }

//    private void setTagRecycler(List<eloItems> tagList) {
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        tagRecycler = findViewById(R.id.eloTagRecycler);
//        tagRecycler.setLayoutManager(layoutManager);
//        eloItemAdapter = new eloItemAdapter(this, tagList);
//        tagRecycler.setAdapter(eloItemAdapter);
//    }
}
