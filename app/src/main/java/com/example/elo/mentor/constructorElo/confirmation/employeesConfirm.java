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
import com.example.elo.mentor.constructorElo.employees;

public class employeesConfirm extends AppCompatActivity {
    private String userName;
    private String userType;
    final Context context = this;

    public employeesConfirm() {};

    public employeesConfirm(String userName, String userType)  {
        this.userName = userName;
        this.userType = userType;
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

        ListView employeesListView = findViewById(R.id.listView);

        employeesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        employeesConfirm s = new employeesConfirm("Самвел Семенов","junior");
        employeesConfirm mx = new employeesConfirm("Максим Максим","junior");
        employeesConfirm k = new employeesConfirm("Кирилл Широбоков","junior");
        employeesConfirm ak = new employeesConfirm("Alex Kiselev","junior");
        employeesConfirm rg = new employeesConfirm("RUSTAM GPOWER","junior");
        employeesConfirm b = new employeesConfirm("Богдан Бельский","junior");
        employeesConfirm w = new employeesConfirm("Worker Name","junior");


        employeesConfirm[] users = new employeesConfirm[]{s, mx, k, ak, rg, b, w};
        ArrayAdapter<employeesConfirm> arrayAdapter
                = new ArrayAdapter<employeesConfirm>(this, android.R.layout.simple_list_item_1, users);

        employeesListView.setAdapter(arrayAdapter);

    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NonNull
    @Override
    public String toString() { return this.userName +" ("+ this.userType+")"; }
}
