package com.example.elo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.MainActivity;
import com.example.elo.R;
import com.example.elo.eloCreated;
import com.example.elo.eloPart;
import com.example.elo.model.DeleteIcons;
import com.example.elo.model.tagCategory;

import java.util.List;

public class deleteIconsWorkAdapter extends RecyclerView.Adapter<deleteIconsWorkAdapter.CategoryViewHolder>{

    Context context;
    List<DeleteIcons> ids;

    public deleteIconsWorkAdapter(Context context, List<DeleteIcons> ids) {
        this.context = context;
        this.ids = ids;
    }

    @NonNull
    @Override
    public deleteIconsWorkAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Items = LayoutInflater.from(context).inflate(R.layout.delete_list_short, parent, false);
        return new deleteIconsWorkAdapter.CategoryViewHolder(Items);
    }

    @Override
    public void onBindViewHolder(@NonNull deleteIconsWorkAdapter.CategoryViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eloPart.removeElo(ids.get(position).getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder {
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
