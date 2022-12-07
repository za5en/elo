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
import com.example.elo.manageElo;
import com.example.elo.model.EloProfile;

import java.util.List;

public class adminEloProfileAdapter extends RecyclerView.Adapter<adminEloProfileAdapter.EloViewHolder> {

    Context context;
    List<EloProfile> eloList;

    public adminEloProfileAdapter(Context context, List<EloProfile> eloList) {
        this.context = context;
        this.eloList = eloList;
    }

    @NonNull
    @Override
    public EloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eloItems = LayoutInflater.from(context).inflate(R.layout.admin_elo_item, parent, false);
        return new adminEloProfileAdapter.EloViewHolder(eloItems);
    }

    @Override
    public void onBindViewHolder(@NonNull adminEloProfileAdapter.EloViewHolder holder, int position) {

        holder.eloName.setText(eloList.get(position).getName());
        holder.eloDescText.setText(eloList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, manageElo.class);

                intent.putExtra("previewName", eloList.get(position).getPreviewName());
                intent.putExtra("previewDesc", eloList.get(position).getPreviewDesc());
                intent.putExtra("id", eloList.get(position).getId());

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
