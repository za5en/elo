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

public class requests extends AppCompatActivity {

    private String userName;
    private String userType;
    public static final String tag = "requests";
    private ListView requestsListView;
    final Context context = this;

    public requests() {};

    public requests(String userName, String userType)  {
        this.userName = userName;
        this.userType = userType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests);
        Button back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        this.requestsListView = findViewById(R.id.listView);

        this.requestsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        requests m = new requests("Mikhail","front junior");
        requests r = new requests("RUSTAM","back senior");
        requests s = new requests("Sumvel","back middle");

        requests[] users = new requests[]{m, r, s};
        ArrayAdapter<requests> arrayAdapter
                = new ArrayAdapter<requests>(this, android.R.layout.simple_list_item_1, users);

        this.requestsListView.setAdapter(arrayAdapter);

        this.requestsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(tag, "onItemClick: " +position);
                requests user = (requests) requestsListView.getItemAtPosition(position);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.request_dial, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Одобрить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "сотруднику предоставлен доступ к ЭлО", Toast.LENGTH_SHORT).show();
                                requestsListView.invalidateViews();
                            }
                        })
                        .setNegativeButton("Отклонить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "заявка на вступление отклонена", Toast.LENGTH_SHORT).show();
                                requestsListView.invalidateViews();
                            }
                        });

                dialBuilder.create().show();
            }
        });

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