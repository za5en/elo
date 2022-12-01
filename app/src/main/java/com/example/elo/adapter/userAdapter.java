package com.example.elo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.R;
import com.example.elo.manageUsers;
import com.example.elo.model.Users;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.UserViewHolder> {

    Context context;
    List<Users> userList;

    public userAdapter(Context context, List<Users> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public userAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userItems = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new userAdapter.UserViewHolder(userItems);
    }

    @Override
    public void onBindViewHolder(@NonNull userAdapter.UserViewHolder holder, int position) {

        holder.userName.setText(userList.get(position).getUsername());
        holder.userLevel.setText(userList.get(position).getUserLevel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, manageUsers.class); //add changeuserinfo page

                intent.putExtra("userEmail", userList.get(position).getEmail());
                intent.putExtra("userPassword", userList.get(position).getPassword());
                intent.putExtra("id", userList.get(position).getId());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static final class UserViewHolder extends RecyclerView.ViewHolder {

        LinearLayout userBg;
        TextView userName, userLevel;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userBg = itemView.findViewById(R.id.userBg);
            userName = itemView.findViewById(R.id.userName);
            userLevel = itemView.findViewById(R.id.userLevel);
        }
    }
}