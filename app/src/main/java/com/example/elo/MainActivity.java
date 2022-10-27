package com.example.elo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    final Context context = this;

    Button tags, employees, tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tags = (Button) findViewById(R.id.buttonTags);
        employees = (Button) findViewById(R.id.buttonEmpl);
        tasks = (Button) findViewById(R.id.buttonTasks);


        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, tags.class);
                startActivity(intent);
            }
        });

        employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, employees.class);
                startActivity(intent);
            }
        });

        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, tasks.class);
                startActivity(intent);
            }
        });


    }


}

