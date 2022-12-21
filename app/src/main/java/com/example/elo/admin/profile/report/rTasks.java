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

public class rTasks extends AppCompatActivity {
    final Context context = this;
    DatabaseHelper DbHelper;
    SQLiteDatabase db;
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
        setContentView(R.layout.reports_page_tasks);
        DbHelper = new DatabaseHelper(context);
        db = DbHelper.getWritableDatabase();

        EditText taskId = findViewById(R.id.login);
        EditText eloId = findViewById(R.id.login1);

        RadioGroup rgSort = findViewById(R.id.rgSort);

        ImageButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button all = findViewById(R.id.btnAll);
        all.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                columns = new String[] { "task_id", "elo_id", "task_name", "task_info", "task_url" };
                Cursor cursor = db.query(DatabaseHelper.DB_ELO_TASKS, columns, null, null, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            String str = "";
                            for (String cn : cursor.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
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
                int eloId = 0;
                columns = new String[] { "elo_id" };
                selection = "task_id = ?";
                selectionArgs = new String[] { taskId.getText().toString() };
                Cursor cursor = db.query(DatabaseHelper.DB_ELO_TASKS, columns, selection, selectionArgs, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str = "";
                        for (String cn : cursor.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                        }
                        Log.d("All:", str);
                        eloId = cursor.getInt(0);
                    }
                    cursor.close();
                }

                columns = new String[] { "elo_name", "elo_tag1", "elo_tag2", "elo_tag3" };
                selection = "elo_id = ?";
                selectionArgs = new String[] { String.valueOf(eloId) };
                cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str = "";
                        for (String cn : cursor.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                        }
                        Log.d("All:", str);
                    }
                    cursor.close();
                }
            }
        });

        Button part = findViewById(R.id.btnPart);
        part.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                switch (rgSort.getCheckedRadioButtonId()) {
                    case R.id.rAmount:
                        groupBy1 = "elo_name";
                        orderBy1 = "elo_name";
                        groupBy2 = null;
                        orderBy2 = null;
                        groupBy3 = null;
                        orderBy3 = null;
                        break;
                    case R.id.rElo:
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
                String wholeAmount = null;
                String task_count = "select count(*) from elo_tasks where elo_id = '" + eloId.getText().toString() + "' order by task_id;";
                Cursor cursor = db.rawQuery(task_count, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        wholeAmount = cursor.getString(0);
                        Log.d("Amount:", wholeAmount);
                    }
                    cursor.close();
                }
            }
        });

        Button progress = findViewById(R.id.btnProgress);
        progress.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"NonConstantResourceId", "Range"})
            @Override
            public void onClick(View v) {
                switch (rgSort.getCheckedRadioButtonId()) {
                    case R.id.rAmount:
                        groupBy1 = "elo_name";
                        orderBy1 = "elo_name";
                        groupBy2 = null;
                        orderBy2 = null;
                        groupBy3 = null;
                        orderBy3 = null;
                        break;
                    case R.id.rElo:
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
                //search for users id and task id
                String[] users = new String[20];
                String[] tasks = new String[20];
                int k = 0;
                columns = new String[] { "user_id", "task_id" };
                selection = "elo_id = ?";
                selectionArgs = new String[] { eloId.getText().toString() };
                Cursor cursor = db.query(DatabaseHelper.DB_TASK_PROGRESS, columns, selection, selectionArgs, groupBy2, null, orderBy2);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            String str = "";
                            for (String cn : cursor.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
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
                String task_count = "select count(*) from elo_tasks where elo_id = '" + eloId.getText().toString() + "';";
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
                    cursor = db.query(DatabaseHelper.DB_USERS, columns, selection, selectionArgs, groupBy3, null, orderBy3);
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            String str = "";
                            for (String cn : cursor.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                            }
                            Log.d("All:", str);
                            assert wholeAmount != null;
                            Log.d("progress: ", tasks[i] + "/" + wholeAmount + " (" + Double.parseDouble(tasks[i])/Double.parseDouble(wholeAmount) * 100 + "%)");
                        }
                        cursor.close();
                    }
                }
            }
        });
    }
}
