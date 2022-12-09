package com.example.elo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.R;
import com.example.elo.admin.adminSearch;
import com.example.elo.model.tagCategory;

import java.util.List;

public class tagAdminSearchAdapter extends RecyclerView.Adapter<tagAdminSearchAdapter.CategoryViewHolder>{

    Context context;
    List<tagCategory> categories;

    public tagAdminSearchAdapter(Context context, List<tagCategory> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.tag_item, parent, false);
        return new CategoryViewHolder(categoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull tagAdminSearchAdapter.CategoryViewHolder holder, int position) {
        holder.catName.setText(categories.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminSearch.showEloByCategory(categories.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView catName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.catName);
        }
    }
}
