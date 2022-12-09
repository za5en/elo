package com.example.elo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elo.R;
import com.example.elo.model.Notifications;
import com.example.elo.mentor.notification.notificationPage;

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
        if (nfList.get(position).isRead())
            holder.point.setImageResource(R.drawable.nf_read);
        else
            holder.point.setImageResource(R.drawable.nf_unread);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.point.setImageResource(R.drawable.nf_read);
                Intent intent = new Intent(context, notificationPage.class);

                intent.putExtra("fullText", nfList.get(position).getFullText());
                intent.putExtra("text", nfList.get(position).getText());
                intent.putExtra("theme", nfList.get(position).getTheme());
                intent.putExtra("id", nfList.get(position).getId());
                intent.putExtra("date", nfList.get(position).getDate());
                intent.putExtra("eloDesc", nfList.get(position).getEloDesc());

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
