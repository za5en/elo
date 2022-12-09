package com.example.elo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.R;
import com.example.elo.model.eloItems;

import java.util.List;

public class eloItemAdapter extends RecyclerView.Adapter<eloItemAdapter.CategoryViewHolder>{


    Context context;
    List<eloItems> tags;

    public eloItemAdapter(Context context, List<eloItems> tags) {
        this.context = context;
        this.tags = tags;
    }

    @NonNull
    @Override
    public eloItemAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tagItems = LayoutInflater.from(context).inflate(R.layout.tag_item, parent, false);
        return new eloItemAdapter.CategoryViewHolder(tagItems);
    }

    @Override
    public void onBindViewHolder(@NonNull eloItemAdapter.CategoryViewHolder holder, int position) {
        holder.tagName.setText(tags.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tagName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tagName = itemView.findViewById(R.id.catName);
        }
    }
}
