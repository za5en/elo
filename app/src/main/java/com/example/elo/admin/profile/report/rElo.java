package com.example.elo.admin.profile.report;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;

public class rElo extends AppCompatActivity {
    final Context context = this;
    DatabaseHelper DbHelper;
    Cursor c = null;
    EditText name;
    String[] columns = {null};
    String selection = null;
    String[] selectionArgs = null;
    String groupBy1 = null;
    String orderBy1 = null;
    String groupBy2 = null;
    String orderBy2 = null;
    String groupBy3 = null;
    String orderBy3 = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reports_page_elo);
        DbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = DbHelper.getWritableDatabase();

        ImageButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        name.findViewById(R.id.login);

        RadioGroup rgSort = findViewById(R.id.rgSort);

        Button all = findViewById(R.id.btnAll);
        all.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                columns = new String[] { "elo_id", "elo_name", "elo_short_info", "elo_info", "elo_availability", "elo_owner_id", "elo_tag1", "elo_tag2", "elo_tag3" };
                Cursor cursor = db.query(DatabaseHelper.DB_ELO, columns, null, null, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            String str = "";
                            for (String cn : c.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + c.getString(c.getColumnIndex(cn)) + "; ");
                            }
                            Log.d("All:", str);

                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }
            }
        });

        Button owner = findViewById(R.id.btnOwner);
        owner.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                String ownerId = null;
                columns = new String[] { "elo_owner_id" };
                selection = "elo_name = ?";
                selectionArgs = new String[] { name.getText().toString() };
                Cursor cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str = "";
                        for (String cn : c.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + c.getString(c.getColumnIndex(cn)) + "; ");
                        }
                        ownerId = cursor.getString(0);
                        Log.d("All:", str);
                    }
                    cursor.close();
                }
                columns = new String[] { "user_type", "user_name", "user_surname" };
                selection = "user_id = ?";
                selectionArgs = new String[] { ownerId };
                cursor = db.query(DatabaseHelper.DB_USERS, columns, selection, selectionArgs, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str = "";
                        for (String cn : c.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + c.getString(c.getColumnIndex(cn)) + "; ");
                        }
                        Log.d("All:", str);
                    }
                    cursor.close();
                }
            }
        });

        Button part = findViewById(R.id.btnPart);
        part.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"NonConstantResourceId", "Range"})
            @Override
            public void onClick(View v) {
                switch (rgSort.getCheckedRadioButtonId()) {
                    case R.id.rElo:
                        groupBy1 = "elo_name";
                        orderBy1 = "elo_name";
                        groupBy2 = null;
                        orderBy2 = null;
                        break;
                    case R.id.rUser:
                        groupBy1 = null;
                        orderBy1 = null;
                        groupBy2 = "user_name";
                        orderBy2 = "user_name";
                        break;
                }
                //search for elo id
                String eloId = null;
                columns = new String[] { "elo_id" };
                selection = "elo_name = ?";
                selectionArgs = new String[] { name.getText().toString() };
                Cursor cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, groupBy1, null, orderBy1);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str = "";
                        for (String cn : c.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + c.getString(c.getColumnIndex(cn)) + "; ");
                        }
                        eloId = cursor.getString(0);
                        Log.d("All:", str);
                    }
                    cursor.close();
                }
                //search for users id
                String[] users = new String[20];
                int k = 0;
                columns = new String[] { "user_id" };
                selection = "elo_id = ?";
                selectionArgs = new String[] { eloId };
                cursor = db.query(DatabaseHelper.DB_REL_LIST, columns, selection, selectionArgs, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            String str = "";
                            for (String cn : c.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + c.getString(c.getColumnIndex(cn)) + "; ");
                            }
                            users[k++] = cursor.getString(0);
                            Log.d("All:", str);
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }
                //search for users info
                columns = new String[] { "user_type", "user_name", "user_surname", "user_level" };
                selection = "user_id = ?";
                for (int i = 0; i < k; i++) {
                    selectionArgs = new String[] { users[i] };
                    cursor = db.query(DatabaseHelper.DB_USERS, columns, selection, selectionArgs, groupBy2, null, orderBy2);
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            String str = "";
                            for (String cn : c.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + c.getString(c.getColumnIndex(cn)) + "; ");
                            }
                            Log.d("All:", str);
                        }
                        cursor.close();
                    }
                }
            }
        });

        Button progress = findViewById(R.id.btnProgress);
        progress.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"NonConstantResourceId", "Range"})
            @Override
            public void onClick(View v) {
                switch (rgSort.getCheckedRadioButtonId()) {
                    case R.id.rElo:
                        groupBy1 = "elo_name";
                        orderBy1 = "elo_name";
                        groupBy2 = null;
                        orderBy2 = null;
                        groupBy3 = null;
                        orderBy3 = null;
                        break;
                    case R.id.rUser:
                        groupBy1 = null;
                        orderBy1 = null;
                        groupBy2 = "user_name";
                        orderBy2 = "user_name";
                        groupBy3 = null;
                        orderBy3 = null;
                        break;
                    case R.id.rTask:
                        groupBy1 = null;
                        orderBy1 = null;
                        groupBy2 = null;
                        orderBy2 = null;
                        groupBy3 = "task_id";
                        orderBy3 = "task_id";
                        break;
                }
                //search for elo id
                String eloId = null;
                columns = new String[] { "elo_id" };
                selection = "elo_name = ?";
                selectionArgs = new String[] { name.getText().toString() };
                Cursor cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, groupBy1, null, orderBy1);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str = "";
                        for (String cn : c.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + c.getString(c.getColumnIndex(cn)) + "; ");
                        }
                        eloId = cursor.getString(0);
                        Log.d("All:", str);
                    }
                    cursor.close();
                }
                //search for users id and task id
                String[] users = new String[20];
                String[] tasks = new String[20];
                int k = 0;
                columns = new String[] { "user_id", "task_id" };
                selection = "elo_id = ?";
                selectionArgs = new String[] { eloId };
                cursor = db.query(DatabaseHelper.DB_TASK_PROGRESS, columns, selection, selectionArgs, groupBy2, null, orderBy2);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            String str = "";
                            for (String cn : c.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + c.getString(c.getColumnIndex(cn)) + "; ");
                            }
                            users[k] = cursor.getString(0);
                            tasks[k++] = cursor.getString(1);
                            Log.d("All:", str);
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }
                //search for tasks amount
                String wholeAmount = null;
                String task_count = "select count(*) from elo_tasks where elo_id = '" + eloId + "';";
                cursor = db.rawQuery(task_count, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        wholeAmount = cursor.getString(0);
                    }
                    cursor.close();
                }
                //search for users info
                columns = new String[] { "user_type", "user_name", "user_surname", "user_level" };
                selection = "user_id = ?";
                for (int i = 0; i < k; i++) {
                    selectionArgs = new String[] { users[i] };
                    cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, groupBy3, null, orderBy3);
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            String str = "";
                            for (String cn : c.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + c.getString(c.getColumnIndex(cn)) + "; ");
                            }
                            Log.d("All:", str);
                            assert wholeAmount != null;
                            Log.d("progress: ", tasks[i] + "/" + wholeAmount + " (" + Integer.parseInt(tasks[i])/Integer.parseInt(wholeAmount)*100 + "%)");
                        }
                        cursor.close();
                    }
                }
            }
        });
    }
}
