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

import com.example.elo.mentor.eloPage.EloInfo;
import com.example.elo.R;
import com.example.elo.model.Elos;

import java.util.List;

public class eloAdapter extends RecyclerView.Adapter<eloAdapter.EloViewHolder> {

    Context context;
    List<Elos> eloList;

    public eloAdapter(Context context, List<Elos> eloList) {
        this.context = context;
        this.eloList = eloList;
    }

    public void setFilteredList (List<Elos> filteredList) {
        this.eloList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eloItems = LayoutInflater.from(context).inflate(R.layout.elo_item, parent, false);
        return new eloAdapter.EloViewHolder(eloItems);
    }

    @Override
    public void onBindViewHolder(@NonNull eloAdapter.EloViewHolder holder, int position) {

        holder.eloName.setText(eloList.get(position).getName());
        holder.eloDescText.setText(eloList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EloInfo.class);

                intent.putExtra("previewName", eloList.get(position).getPreviewName());
                intent.putExtra("previewDesc", eloList.get(position).getPreviewDesc());
                intent.putExtra("id", eloList.get(position).getId());
                intent.putExtra("isPrivate", eloList.get(position).isPrivate());
                intent.putExtra("userId", eloList.get(position).getUserId());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eloList.size();
    }

    public static final class EloViewHolder extends RecyclerView.ViewHolder {

        LinearLayout eloBg;
        TextView eloName, eloDescText;

        public EloViewHolder(@NonNull View itemView) {
            super(itemView);

            eloBg = itemView.findViewById(R.id.eloBg);
            eloName = itemView.findViewById(R.id.eloName);
            eloDescText = itemView.findViewById(R.id.eloDescText);
        }
    }
}
