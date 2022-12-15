package com.example.elo.mentor.constructorElo.confirmation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;

public class tasksConfirm extends AppCompatActivity {

    private String taskName;
    final Context context = this;

    public tasksConfirm() {};

    public tasksConfirm(String taskName)  {
        this.taskName = taskName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_confirm);

        Button back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ListView tasksListView = findViewById(R.id.listView);

        tasksListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        tasksConfirm t1 = new tasksConfirm("Задание 1");
        tasksConfirm t2 = new tasksConfirm("Задание 2");
        tasksConfirm t3 = new tasksConfirm("Задание 3");
        tasksConfirm t4 = new tasksConfirm("Задание 4");
        tasksConfirm t5 = new tasksConfirm("Задание 5");
        tasksConfirm t6 = new tasksConfirm("Задание 6");
        tasksConfirm t7 = new tasksConfirm("Итоговое задание");

        tasksConfirm[] users = new tasksConfirm[]{t1, t2, t3, t4, t5, t6, t7};
        ArrayAdapter<tasksConfirm> arrayAdapter
                = new ArrayAdapter<tasksConfirm>(this, android.R.layout.simple_list_item_1, users);

        tasksListView.setAdapter(arrayAdapter);

    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @NonNull
    @Override
    public String toString() { return this.taskName; }
}
