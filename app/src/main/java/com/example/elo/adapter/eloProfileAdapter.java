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

import com.example.elo.EloInfo;
import com.example.elo.R;
import com.example.elo.model.EloProfile;
import com.example.elo.model.Elos;

import java.util.List;

public class eloProfileAdapter extends RecyclerView.Adapter<eloProfileAdapter.EloViewHolder> {

    Context context;
    List<EloProfile> eloList;

    public eloProfileAdapter(Context context, List<EloProfile> eloList) {
        this.context = context;
        this.eloList = eloList;
    }

    @NonNull
    @Override
    public EloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eloItems = LayoutInflater.from(context).inflate(R.layout.elo_item_profile, parent, false);
        return new eloProfileAdapter.EloViewHolder(eloItems);
    }

    @Override
    public void onBindViewHolder(@NonNull eloProfileAdapter.EloViewHolder holder, int position) {

        holder.eloName.setText(eloList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EloInfo.class);
//
//                intent.putExtra("previewName", eloList.get(position).getPreviewName());
//                intent.putExtra("eloPreviewDesc", eloList.get(position).getPreviewDesc());
//                intent.putExtra("eloId", eloList.get(position).getId());
//
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
        TextView eloName, eloProgress;

        public EloViewHolder(@NonNull View itemView) {
            super(itemView);

            eloBg = itemView.findViewById(R.id.eloBg);
            eloName = itemView.findViewById(R.id.eloName);
            eloProgress = itemView.findViewById(R.id.percents);
        }
    }
}
