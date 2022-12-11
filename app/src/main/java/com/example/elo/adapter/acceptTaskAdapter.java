package com.example.elo.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.mentor.eloPage.EloInfo;
import com.example.elo.R;
import com.example.elo.mentor.manage.pages.acceptTask;
import com.example.elo.model.AcceptTask;
import com.example.elo.model.Elos;

import java.util.List;

public class acceptTaskAdapter extends RecyclerView.Adapter<acceptTaskAdapter.TaskViewHolder> {

    Context context;
    List<AcceptTask> tasks;

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
        holder.taskName.setText(tasks.get(position).getUserTask());

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
                            }
                        })
                        .setNegativeButton("Отклонить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "выполнение не подтверждено", Toast.LENGTH_SHORT).show();
                                acceptTask.removeTasks(tasks.get(position).getId());
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
