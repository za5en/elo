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
import com.example.elo.admin.profile.manage.changeInfo;
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
        holder.userAccessLevel.setText(userList.get(position).getUserAccessLevel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userList.get(position).getUserAccessLevel().equals("Admin")) {
                    Intent intent = new Intent(context, changeInfo.class);

                    intent.putExtra("userName", userList.get(position).getUsername());
                    intent.putExtra("userLevel", userList.get(position).getUserLevel());
                    intent.putExtra("userEmail", userList.get(position).getEmail());
                    intent.putExtra("userPassword", userList.get(position).getPassword());
                    intent.putExtra("changeInfoId", userList.get(position).getId());
                    intent.putExtra("userAccessLevel", userList.get(position).getUserAccessLevel());
                    intent.putExtra("tag1", userList.get(position).getTag1());
                    intent.putExtra("tag2", userList.get(position).getTag2());
                    intent.putExtra("tag3", userList.get(position).getTag3());

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static final class UserViewHolder extends RecyclerView.ViewHolder {

        LinearLayout userBg;
        TextView userName, userAccessLevel;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userBg = itemView.findViewById(R.id.userBg);
            userName = itemView.findViewById(R.id.userName);
            userAccessLevel = itemView.findViewById(R.id.userAccessLevel);
        }
    }
}