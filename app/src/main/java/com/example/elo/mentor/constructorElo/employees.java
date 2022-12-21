package com.example.elo.mentor.constructorElo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;

public class employees extends AppCompatActivity {

    private String userName;
    private String userType;
    private boolean active;
    public static final String tag = "employees";
    private ListView employeeListView;

    public employees() {};

    public employees(String userName, String userType)  {
        this.userName = userName;
        this.userType = userType;
        this.active = true;
    }

    public employees(String userName, String userType, boolean active)  {
        this.userName = userName;
        this.userType = userType;
        this.active = active;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employees);
        Button back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        this.employeeListView = findViewById(R.id.listView);

        this.employeeListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.initListViewData();

        this.employeeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(tag, "onItemClick: " +position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                employees user = (employees) employeeListView.getItemAtPosition(position);
                user.setActive(!currentCheck);
            }
        });

    }

    private void initListViewData()  {
        employees m = new employees("Михаил Михайлович","senior", false);
        employees r = new employees("Рустам Авангард","senior",false);
        employees f = new employees("Фёдор Власов","senior", false);
        employees v = new employees("Виталий Новый","senior", false);
        employees a = new employees("Артём Коновалов","middle", false);
        employees d = new employees("Дима Перевозчиков","middle", false);
        employees s = new employees("Самвел Семенов","junior", false);
        employees mx = new employees("Максим Максим","junior", false);
        employees k = new employees("Кирилл Широбоков","junior", false);
        employees ak = new employees("Alex Kiselev","junior", false);
        employees rg = new employees("RUSTAM GPOWER","junior", false);
        employees b = new employees("Богдан Бельский","junior", false);
        employees w = new employees("Worker Name","junior", false);

        employees[] users = new employees[]{r, f, m, v, a, d, s, mx, k, ak, rg, b, w};
        ArrayAdapter<employees> arrayAdapter
                = new ArrayAdapter<employees>(this, android.R.layout.simple_list_item_multiple_choice, users);

        this.employeeListView.setAdapter(arrayAdapter);

        for(int i = 0; i < users.length; i++)  {
            this.employeeListView.setItemChecked(i, users[i].isActive());
        }
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

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    @NonNull
    @Override
    public String toString() { return this.userName +" ("+ this.userType+")"; }
}