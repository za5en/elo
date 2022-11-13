package com.example.elo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.model.Tasks_elo;

public class acceptTask extends AppCompatActivity {

    private String userName;
    private String userTask;
    public static final String tag = "acceptTask";
    private ListView acceptTaskListView;
    final Context context = this;

    public acceptTask() {};

    public acceptTask(String userName, String userTask)  {
        this.userName = userName;
        this.userTask = userTask;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_task);
        Button back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        this.acceptTaskListView = findViewById(R.id.listView);

        this.acceptTaskListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        acceptTask m = new acceptTask("Mikhail","Задание 5, C#");
        acceptTask r = new acceptTask("RUSTAM","Задание 1, Java");
        acceptTask s = new acceptTask("Sumvel","Задание 3, SQL");

        acceptTask[] users = new acceptTask[]{m, r, s};
        ArrayAdapter<acceptTask> arrayAdapter
                = new ArrayAdapter<acceptTask>(this, android.R.layout.simple_list_item_1, users);

        this.acceptTaskListView.setAdapter(arrayAdapter);

        this.acceptTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(tag, "onItemClick: " +position);
                acceptTask user = (acceptTask) acceptTaskListView.getItemAtPosition(position);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.accept_task_dial, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "выполнение подтверждено", Toast.LENGTH_SHORT).show();
                                acceptTaskListView.invalidateViews();
                            }
                        })
                        .setNegativeButton("Отклонить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "выполнение не подтверждено", Toast.LENGTH_SHORT).show();
                                acceptTaskListView.invalidateViews();
                            }
                        });

                dialBuilder.create().show();
            }
        });

    }

    public String getuserTask() {
        return userTask;
    }

    public void setuserTask(String userTask) {
        this.userTask = userTask;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NonNull
    @Override
    public String toString() { return this.userName +" ("+ this.userTask+")"; }
}