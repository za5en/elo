package com.example.elo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.R;
import com.example.elo.model.userTypes;

import java.util.List;

public class typeAdapter extends RecyclerView.Adapter<typeAdapter.CategoryViewHolder>{

    Context context;
    List<userTypes> types;

    public typeAdapter(Context context, List<userTypes> types) {
        this.context = context;
        this.types = types;
    }

    @NonNull
    @Override
    public typeAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View typeItems = LayoutInflater.from(context).inflate(R.layout.tag_item, parent, false);
        return new typeAdapter.CategoryViewHolder(typeItems);
    }

    @Override
    public void onBindViewHolder(@NonNull typeAdapter.CategoryViewHolder holder, int position) {
        holder.catName.setText(types.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView catName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.catName);
        }
    }
}