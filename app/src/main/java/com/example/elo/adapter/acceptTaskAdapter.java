package com.example.elo.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.DatabaseHelper;
import com.example.elo.mentor.eloPage.EloInfo;
import com.example.elo.R;
import com.example.elo.mentor.manage.pages.acceptTask;
import com.example.elo.model.AcceptTask;
import com.example.elo.model.Elos;

import java.util.List;

public class acceptTaskAdapter extends RecyclerView.Adapter<acceptTaskAdapter.TaskViewHolder> {

    Context context;
    List<AcceptTask> tasks;

    int uId, id;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    ContentValues contentValues = new ContentValues();

    public acceptTaskAdapter(Context context, List<AcceptTask> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    public void setFilteredList (List<AcceptTask> filteredList) {
        this.tasks = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eloItems = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new acceptTaskAdapter.TaskViewHolder(eloItems);
    }

    @Override
    public void onBindViewHolder(@NonNull acceptTaskAdapter.TaskViewHolder holder, int position) {

        holder.userName.setText(tasks.get(position).getUserName());
        String task = "Задание " + tasks.get(position).getUserTask();
        holder.taskName.setText(task);
        id = tasks.get(position).getEloId();
        uId = tasks.get(position).getUserId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.accept_task_dial, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "выполнение подтверждено", Toast.LENGTH_SHORT).show();
                                acceptTask.removeTasks(tasks.get(position).getId());

                                Cursor cursor;
                                String wholeAmount = null, userAmount = null;
                                String task_count = "select count(*) from elo_tasks where elo_id = '" + id + "';";
                                dbHelper = new DatabaseHelper(context);
                                db = dbHelper.getWritableDatabase();
                                cursor = db.rawQuery(task_count, null);
                                if (cursor != null) {
                                    if (cursor.moveToFirst()) {
                                        wholeAmount = cursor.getString(0);
                                    }
                                    cursor.close();
                                }


                                task_count = "select task_id from task_progress where user_id = '"+ uId +"' and elo_id = '"+ id +"';";
                                cursor = db.rawQuery(task_count, null);
                                if (cursor != null) {
                                    if (cursor.moveToFirst()) {
                                        userAmount = cursor.getString(0);
                                    }
                                    cursor.close();
                                }

                                assert userAmount != null;
                                if (userAmount.equals(wholeAmount)) {
                                    contentValues.put(DatabaseHelper.PROGRESS, 2);
                                    db.update(DatabaseHelper.DB_TASK_PROGRESS, contentValues, "user_id = ? AND elo_id = ?", new String[] {String.valueOf(uId), String.valueOf(id)});
                                    db.delete(DatabaseHelper.DB_REL_LIST, "user_id = "+uId+" AND elo_id = "+id, null);
                                }
                                else {
                                    contentValues.put(DatabaseHelper.TASK_ID, Integer.parseInt(userAmount) + 1);
                                    contentValues.put(DatabaseHelper.PROGRESS, 0);
                                    db.update(DatabaseHelper.DB_TASK_PROGRESS, contentValues, "user_id = ? AND elo_id = ?", new String[] {String.valueOf(uId), String.valueOf(id)});
                                }
                            }
                        })
                        .setNegativeButton("Отклонить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "выполнение не подтверждено", Toast.LENGTH_SHORT).show();
                                acceptTask.removeTasks(tasks.get(position).getId());
                                contentValues.put(DatabaseHelper.PROGRESS, 0);
                                db.update(DatabaseHelper.DB_TASK_PROGRESS, contentValues, "user_id = ? AND elo_id = ?", new String[] {String.valueOf(uId), String.valueOf(id)});
                            }
                        });

                dialBuilder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static final class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView userName, taskName;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            taskName = itemView.findViewById(R.id.taskName);
        }
    }
}
