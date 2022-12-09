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

public class tagsConfirm extends AppCompatActivity {

    private String tagsName;
    final Context context = this;

    public tagsConfirm() {};

    public tagsConfirm(String tagsName)  {
        this.tagsName = tagsName;
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

        tagsConfirm j = new tagsConfirm("java");
        tagsConfirm b = new tagsConfirm("back");
        tagsConfirm s = new tagsConfirm("sql");

        tagsConfirm[] users = new tagsConfirm[]{j, b, s};
        ArrayAdapter<tagsConfirm> arrayAdapter
                = new ArrayAdapter<tagsConfirm>(this, android.R.layout.simple_list_item_1, users);

        tasksListView.setAdapter(arrayAdapter);

    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    @NonNull
    @Override
    public String toString() { return this.tagsName; }
}
