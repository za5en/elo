package com.example.elo.admin.profile.manage;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.elo.R;
import com.example.elo.mentor.constructorElo.tags;

import java.util.ArrayList;

public class changeTags extends ListActivity {
    ArrayList<String> tagsList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Button back;
    final Context context = this;
    private String tagName;
    private boolean active;
    public static final String tag = "tags";
    private ListView tagListView;

    public changeTags() {};

    public changeTags(String tagName)  {
        this.tagName = tagName;
        this.active = true;
    }

    public changeTags(String tagName, boolean active)  {
        this.tagName = tagName;
        this.active = active;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.tags);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                tagsList);
        setListAdapter(adapter);
        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        this.tagListView = findViewById(android.R.id.list);

        this.tagListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.initListViewData();

        this.tagListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Log.i(tag, "onItemClick: " + position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                tags tag = (tags) tagListView.getItemAtPosition(position);
                tag.setActive(!currentCheck);
            }
        });
    }

    private void initListViewData()  {
        tags j = new tags("java", true);
        tags sql = new tags("sql", true);
        tags sharp = new tags("c#", true);
        tags f = new tags("front", false);
        tags b = new tags("back", false);
        tags qa = new tags("qa", false);
        tags p = new tags("python", false);
        tags r = new tags("react", false);
        tags other = new tags("другое", false);

        tags[] users = new tags[]{j, sql, sharp, f, b, qa, p, r, other};
        ArrayAdapter<tags> arrayAdapter
                = new ArrayAdapter<tags>(this, android.R.layout.simple_list_item_multiple_choice, users);

        this.tagListView.setAdapter(arrayAdapter);

        for(int i = 0; i < users.length; i++)  {
            this.tagListView.setItemChecked(i, users[i].isActive());
        }
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    @NonNull
    @Override
    public String toString() { return this.tagName; }
}
