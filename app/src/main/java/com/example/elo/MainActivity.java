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

    public static final String tag = "employees";
    final Context context = this;
    private ListView emplListView;

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

            }
        });

//        this.emplListView = (ListView)findViewById(R.id.listView);
//        Button button = (Button)findViewById(R.id.button);
//
//        this.emplListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//
//        this.emplListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i(tag, "onItemClick: " +position);
//                CheckedTextView v = (CheckedTextView) view;
//                boolean currentCheck = v.isChecked();
//                employees user = (employees) emplListView.getItemAtPosition(position);
//                user.setActive(!currentCheck);
//            }
//        });
//
////        button.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(context, MainActivity.class);
////                startActivity(intent);
////            }
////        });
//
//        this.initListViewData();
    }

    public void openTags(View view) {
        Intent intent = new Intent(context, tags.class);
        startActivity(intent);
    }

    public void openTasks(View view) {
        Intent intent = new Intent(context, tasks.class);
        startActivity(intent);
    }

    public void openEmployees(View view) {
        Intent intent = new Intent(context, employees.class);
        startActivity(intent);
    }

//    private void initListViewData()  {
//        employees m = new employees("Mikhail","front junior", false);
//        employees r = new employees("RUSTAM","back senior", false);
//        employees s = new employees("Sumvel","back middle", false);
//
//        employees[] users = new employees[]{m, r, s};
//        ArrayAdapter<employees> arrayAdapter
//                = new ArrayAdapter<employees>(this, android.R.layout.simple_list_item_multiple_choice, users);
//
//        this.emplListView.setAdapter(arrayAdapter);
//
//        for(int i = 0; i < users.length; i++)  {
//            this.emplListView.setItemChecked(i, users[i].isActive());
//        }
//    }
}

