package com.example.elo.mentor.constructorElo;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

import com.example.elo.R;
import com.example.elo.model.Tasks_elo;
import java.util.ArrayList;

public class tasks extends ListActivity {
    ArrayList<String> taskList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Button addTask, back;
    EditText nameInput, descInput;
    final Context context = this;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.tasks);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                taskList);
        setListAdapter(adapter);
        addTask = findViewById(R.id.addTask);
        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.task_dial, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);
                final EditText nameInput = dialView.findViewById(R.id.task_type);
                final EditText descInput = dialView.findViewById(R.id.desc_type);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int task_id = getIntent().getIntExtra("taskId", 0);
                                adapter.add(nameInput.getText().toString());
                                Log.i("AlertDialog",nameInput.getText().toString());
                                Tasks_elo.task_name[task_id] = nameInput.getText().toString();
                                Log.i("AlertDialog",descInput.getText().toString());
                                Tasks_elo.task_desc[task_id] = descInput.getText().toString();
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
}
