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

public class rUsers extends AppCompatActivity {
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
        setContentView(R.layout.reports_page_users);
        DbHelper = new DatabaseHelper(context);
        db = DbHelper.getWritableDatabase();

        ImageButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        EditText name = findViewById(R.id.login);
        EditText surname = findViewById(R.id.login1);

        RadioGroup rgSort = findViewById(R.id.rgSort);

        Button all = findViewById(R.id.btnAll);
        all.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                columns = new String[] { "user_id", "user_type", "user_name", "user_surname", "user_email", "user_password", "user_level", "user_tag1", "user_tag2", "user_tag3" };
                Cursor cursor = db.query(DatabaseHelper.DB_USERS, columns, null, null, null, null, null);
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
                if (rgSort.getCheckedRadioButtonId() == R.id.rElo) {
                    orderBy1 = "elo_name";
                    groupBy1 = "elo_name";
                    String ownerId = null;
                    columns = new String[] { "user_id" };
                    selection = "user_name = ? AND user_surname = ?";
                    selectionArgs = new String[] { name.getText().toString(), surname.getText().toString() };
                    Cursor cursor = db.query(DatabaseHelper.DB_USERS, columns, selection, selectionArgs, null, null, null);
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            String str = "";
                            for (String cn : cursor.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                            }
                            ownerId = cursor.getString(0);
                            Log.d("All:", str);
                        }
                        cursor.close();
                    }

                    columns = new String[] { "elo_id", "elo_name", "elo_availability", "elo_tag1", "elo_tag2", "elo_tag3" };
                    selection = "elo_owner_id = ?";
                    selectionArgs = new String[] { ownerId };

                    cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, groupBy1, null, orderBy1);
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
            }
        });

        Button part = findViewById(R.id.btnPart);
        part.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
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
                }
                String userId = null;
                columns = new String[] { "user_id" };
                selection = "user_name = ? AND user_surname = ?";
                selectionArgs = new String[] { name.getText().toString(), surname.getText().toString() };
                Cursor cursor = db.query(DatabaseHelper.DB_USERS, columns, selection, selectionArgs, groupBy2, null, orderBy2);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str = "";
                        for (String cn : cursor.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                        }
                        userId = cursor.getString(0);
                        Log.d("All:", str);
                    }
                    cursor.close();
                }

                String[] eloId = new String[20];
                int k = 0;
                columns = new String[] { "elo_id" };
                selection = "user_id = ?";
                selectionArgs = new String[] { userId };
                cursor = db.query(DatabaseHelper.DB_REL_LIST, columns, selection, selectionArgs, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            String str = "";
                            for (String cn : cursor.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                            }
                            eloId[k++] = cursor.getString(0);
                            Log.d("All:", str);
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }

                columns = new String[] { "elo_name", "elo_tag1", "elo_tag2", "elo_tag3" };
                selection = "elo_id = ?";
                for (int i = 0; i < k; i++) {
                    selectionArgs = new String[] { eloId[i] };
                    cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, groupBy1, null, orderBy1);
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
            }
        });

        Button progress = findViewById(R.id.btnProgress);
        progress.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                switch (rgSort.getCheckedRadioButtonId()) {
                    case R.id.rElo:
                        groupBy1 = "user_name";
                        orderBy1 = "user_name";
                        groupBy2 = null;
                        orderBy2 = null;
                        groupBy3 = null;
                        orderBy3 = null;
                        break;
                    case R.id.rUser:
                        groupBy1 = null;
                        orderBy1 = null;
                        groupBy2 = "task_id";
                        orderBy2 = "task_id";
                        groupBy3 = null;
                        orderBy3 = null;
                        break;
                    case R.id.rTask:
                        groupBy1 = null;
                        orderBy1 = null;
                        groupBy2 = null;
                        orderBy2 = null;
                        groupBy3 = "elo_name";
                        orderBy3 = "elo_name";
                        break;
                }
                String userId = null;
                columns = new String[] { "user_id" };
                selection = "user_name = ? AND user_surname = ?";
                selectionArgs = new String[] { name.getText().toString(), surname.getText().toString() };
                Cursor cursor = db.query(DatabaseHelper.DB_USERS, columns, selection, selectionArgs, groupBy1, null, orderBy1);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str = "";
                        for (String cn : cursor.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                        }
                        userId = cursor.getString(0);
                        Log.d("All:", str);
                    }
                    cursor.close();
                }

                String[] eloId = new String[20];
                String[] taskId = new String[20];
                int k = 0;
                columns = new String[] { "elo_id", "task_id" };
                selection = "user_id = ?";
                selectionArgs = new String[] { userId };
                cursor = db.query(DatabaseHelper.DB_TASK_PROGRESS, columns, selection, selectionArgs, groupBy2, null, orderBy2);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            String str = "";
                            for (String cn : cursor.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                            }
                            eloId[k] = cursor.getString(0);
                            taskId[k++] = cursor.getString(1);
                            Log.d("All:", str);
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }

                columns = new String[] { "elo_name", "elo_tag1", "elo_tag2", "elo_tag3" };
                selection = "elo_id = ?";
                for (int i = 0; i < k; i++) {
                    String wholeAmount = null;
                    String task_count = "select count(*) from elo_tasks where elo_id = '" + eloId[i] + "';";
                    cursor = db.rawQuery(task_count, null);
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            wholeAmount = cursor.getString(0);
                        }
                        cursor.close();
                    }
                    selectionArgs = new String[] { eloId[i] };
                    cursor = db.query(DatabaseHelper.DB_ELO, columns, selection, selectionArgs, groupBy3, null, orderBy3);
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            String str = "";
                            for (String cn : cursor.getColumnNames()) {
                                str = str.concat(cn + " = "
                                        + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                            }
                            Log.d("All:", str);
                            assert wholeAmount != null;
                            Log.d("progress: ", taskId[i] + "/" + wholeAmount + " (" + Double.parseDouble(taskId[i]) / Double.parseDouble(wholeAmount) * 100 + "%)");
                        }
                        cursor.close();
                    }
                }
            }
        });
    }
}
