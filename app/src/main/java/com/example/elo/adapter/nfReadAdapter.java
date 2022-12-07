package com.example.elo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.EloInfo;
import com.example.elo.R;
import com.example.elo.model.Elos;
import com.example.elo.model.Notifications;
import com.example.elo.notificationPage;

import java.util.List;

public class nfReadAdapter extends RecyclerView.Adapter<nfReadAdapter.NfViewHolder> {

    Context context;
    List<Notifications> nfList;
    ImageView point;

    public nfReadAdapter(Context context, List<Notifications> nfList) {
        this.context = context;
        this.nfList = nfList;
    }

    @NonNull
    @Override
    public nfReadAdapter.NfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eloItems = LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false);
        return new nfReadAdapter.NfViewHolder(eloItems);
    }

    @Override
    public void onBindViewHolder(@NonNull nfReadAdapter.NfViewHolder holder, int position) {

        holder.theme.setText(nfList.get(position).getTheme());
        holder.text.setText(nfList.get(position).getText());
        holder.point.setImageResource(R.drawable.nf_read);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, notificationPage.class);

                intent.putExtra("fullText", nfList.get(position).getFullText());
                intent.putExtra("theme", nfList.get(position).getTheme());
                intent.putExtra("id", nfList.get(position).getId());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nfList.size();
    }

    public static final class NfViewHolder extends RecyclerView.ViewHolder {

        TextView theme, text;
        ImageView point;

        public NfViewHolder(@NonNull View itemView) {
            super(itemView);

            theme = itemView.findViewById(R.id.nfName);
            text = itemView.findViewById(R.id.nfText);
            point = itemView.findViewById(R.id.point);
        }
    }
}
