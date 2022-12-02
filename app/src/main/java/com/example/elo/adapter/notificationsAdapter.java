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
import com.example.elo.model.Elos;
import com.example.elo.model.Notifications;

import java.util.List;

public class notificationsAdapter extends RecyclerView.Adapter<notificationsAdapter.NfViewHolder> {

    Context context;
    List<Notifications> nfList;

    public notificationsAdapter(Context context, List<Notifications> nfList) {
        this.context = context;
        this.nfList = nfList;
    }

    @NonNull
    @Override
    public notificationsAdapter.NfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eloItems = LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false);
        return new notificationsAdapter.NfViewHolder(eloItems);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationsAdapter.NfViewHolder holder, int position) {

        holder.theme.setText(nfList.get(position).getTheme());
        holder.text.setText(nfList.get(position).getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EloInfo.class); //add full nf page

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

        public NfViewHolder(@NonNull View itemView) {
            super(itemView);

            theme = itemView.findViewById(R.id.nfName);
            text = itemView.findViewById(R.id.nfText);
        }
    }
}
