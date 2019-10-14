package com.wildanokt.trasify.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wildanokt.trasify.R;
import com.wildanokt.trasify.model.Trash;

import java.util.List;

public class TrashAdapter extends RecyclerView.Adapter<TrashAdapter.ListViewHolder> {

    private Context context;
    private List<Trash> trashes;

    public TrashAdapter(Context context,List<Trash> trashes) {
        this.trashes = trashes;
        this.context = context;
    }

    @NonNull
    @Override
    public TrashAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrashAdapter.ListViewHolder holder, int position) {
        Trash trash = trashes.get(position);

        int anorganik = (int)Math.round(trash.getAnorganik());
        if(anorganik > 30 && anorganik < 60){
            holder.pg_anorganik.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        }else if (anorganik >= 60){
            holder.pg_anorganik.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        }
        int organik = (int)Math.round(trash.getOrganik());
        if(organik > 30 && organik < 60){
            holder.pg_organik.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        }else if (organik >= 60){
            holder.pg_organik.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        }
        int logam = (int)Math.round(trash.getLogam());
        if(logam > 30 && logam < 60){
            holder.pg_logam.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        }else if (logam >= 60){
            holder.pg_logam.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        }

        holder.tv_lokasi.setText(trash.getLokasi());
        holder.tv_sublokasi.setText(trash.getSubLokasi());
        holder.pg_anorganik.setProgress(anorganik);
        holder.pg_organik.setProgress(organik);
        holder.pg_logam.setProgress(logam);
    }

    @Override
    public int getItemCount() {
        return trashes.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_lokasi, tv_sublokasi;
        ProgressBar pg_anorganik, pg_organik, pg_logam;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_lokasi = itemView.findViewById(R.id.item_lokasi_primary);
            tv_sublokasi = itemView.findViewById(R.id.item_lokasi_secondary);
            pg_anorganik = itemView.findViewById(R.id.item_pg_anorganik);
            pg_organik = itemView.findViewById(R.id.item_pg_organik);
            pg_logam = itemView.findViewById(R.id.item_pg_logam);
        }
    }
}
