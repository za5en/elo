package com.example.elo;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.elo.model.Tasks_elo;

import java.util.ArrayList;

public class tags extends ListActivity {
    ArrayList<String> tagsList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Button addTag, back;
    final Context context = this;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.tags);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                tagsList);
        setListAdapter(adapter);
        addTag = findViewById(R.id.addTag);
        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.tag_dial, null);

                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);
                final EditText input = dialView.findViewById(R.id.tag_type);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //int tag_id = getIntent().getIntExtra("tagId", 0);
                                adapter.add(input.getText().toString());
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                dialBuilder.create().show();
            }
        });
    }
//    public void addItems(View v) {
//        adapter.add("New Item");
//    }
}
