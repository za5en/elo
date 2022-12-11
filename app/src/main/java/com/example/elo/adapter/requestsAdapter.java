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
import com.example.elo.mentor.manage.pages.requests;
import com.example.elo.model.AcceptTask;
import com.example.elo.model.Elos;
import com.example.elo.model.Request;

import java.util.List;

public class requestsAdapter extends RecyclerView.Adapter<requestsAdapter.RequestViewHolder> {

    Context context;
    List<Request> users;

    public requestsAdapter(Context context, List<Request> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eloItems = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new requestsAdapter.RequestViewHolder(eloItems);
    }

    @Override
    public void onBindViewHolder(@NonNull requestsAdapter.RequestViewHolder holder, int position) {

        holder.userName.setText(users.get(position).getUserName());
        holder.userType.setText(users.get(position).getUserType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.request_dial, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Одобрить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Заявка одобрена", Toast.LENGTH_SHORT).show();
                                requests.removeTasks(users.get(position).getId());
                            }
                        })
                        .setNegativeButton("Отклонить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Заявка отклонена", Toast.LENGTH_SHORT).show();
                                requests.removeTasks(users.get(position).getId());
                            }
                        });

                dialBuilder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static final class RequestViewHolder extends RecyclerView.ViewHolder {

        TextView userName, userType;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            userType = itemView.findViewById(R.id.userAccessLevel);
        }
    }
}
