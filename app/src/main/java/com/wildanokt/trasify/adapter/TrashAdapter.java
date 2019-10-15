package com.wildanokt.trasify.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
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
        LayerDrawable clAnorganik = (LayerDrawable) holder.pg_anorganik.getProgressDrawable();
        Drawable bgAnorganik = clAnorganik.getDrawable(0);
        Drawable fgAnorganik = clAnorganik.getDrawable(1);
        if(anorganik > 50 && anorganik < 80){
            bgAnorganik.setColorFilter(Color.parseColor("#FAF3B6"), PorterDuff.Mode.SRC_IN);
            fgAnorganik.setColorFilter(Color.parseColor("#FFEB3B"), PorterDuff.Mode.SRC_IN);
        }else if (anorganik >= 80){
            bgAnorganik.setColorFilter(Color.parseColor("#F2B9B3"), PorterDuff.Mode.SRC_IN);
            fgAnorganik.setColorFilter(Color.parseColor("#E84C3D"), PorterDuff.Mode.SRC_IN);

            holder.tv_warn_anor.setText("Sampah Anorganik penuh!");
            holder.tv_warn_anor.setVisibility(View.VISIBLE);
            holder.tv_warn_anor.setTextColor(Color.parseColor("#E84C3D"));
        }

        int organik = (int)Math.round(trash.getOrganik());
        LayerDrawable clOrganik = (LayerDrawable) holder.pg_organik.getProgressDrawable();
        Drawable bgOrganik = clOrganik.getDrawable(0);
        Drawable fgOrganik = clOrganik.getDrawable(1);
        if(organik > 50 && organik < 80){
            bgOrganik.setColorFilter(Color.parseColor("#FAF3B6"), PorterDuff.Mode.SRC_IN);
            fgOrganik.setColorFilter(Color.parseColor("#FFEB3B"), PorterDuff.Mode.SRC_IN);
        }else if (organik >= 80){
            bgOrganik.setColorFilter(Color.parseColor("#F2B9B3"), PorterDuff.Mode.SRC_IN);
            fgOrganik.setColorFilter(Color.parseColor("#E84C3D"), PorterDuff.Mode.SRC_IN);

            holder.tv_warn_or.setText("Sampah Organik penuh!");
            holder.tv_warn_or.setVisibility(View.VISIBLE);
            holder.tv_warn_or.setTextColor(Color.parseColor("#E84C3D"));
        }

        int logam = (int)Math.round(trash.getLogam());
        LayerDrawable clLogam = (LayerDrawable) holder.pg_logam.getProgressDrawable();
        Drawable bgLogam = clLogam.getDrawable(0);
        Drawable fgLogam = clLogam.getDrawable(1);
        if(logam > 50 && logam < 80){
            bgLogam.setColorFilter(Color.parseColor("#FAF3B6"), PorterDuff.Mode.SRC_IN);
            fgLogam.setColorFilter(Color.parseColor("#FFEB3B"), PorterDuff.Mode.SRC_IN);
        }else if (logam >= 80){
            bgLogam.setColorFilter(Color.parseColor("#F2B9B3"), PorterDuff.Mode.SRC_IN);
            fgLogam.setColorFilter(Color.parseColor("#E84C3D"), PorterDuff.Mode.SRC_IN);

            holder.tv_warn_lg.setText("Sampah Logam penuh!");
            holder.tv_warn_lg.setVisibility(View.VISIBLE);
            holder.tv_warn_lg.setTextColor(Color.parseColor("#E84C3D"));
        }

        holder.tv_lokasi.setText(trash.getLokasi());
        holder.tv_sublokasi.setText(trash.getSubLokasi());
        holder.pg_anorganik.setProgress(anorganik);
        holder.tv_anorganik.setText(Integer.toString(anorganik)+"%");
        holder.pg_organik.setProgress(organik);
        holder.tv_organik.setText(Integer.toString(organik)+"%");
        holder.pg_logam.setProgress(logam);
        holder.tv_logam.setText(Integer.toString(logam)+"%");
    }

    @Override
    public int getItemCount() {
        return trashes.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_lokasi, tv_sublokasi, tv_organik, tv_anorganik, tv_logam, tv_warn_anor, tv_warn_or, tv_warn_lg;
        ProgressBar pg_anorganik, pg_organik, pg_logam;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_lokasi = itemView.findViewById(R.id.item_lokasi_primary);
            tv_sublokasi = itemView.findViewById(R.id.item_lokasi_secondary);

            pg_anorganik = itemView.findViewById(R.id.item_pg_anorganik);
            tv_anorganik = itemView.findViewById(R.id.item_persen_anorganik);
            tv_warn_anor = itemView.findViewById(R.id.tv_warn_anorganik);

            pg_organik = itemView.findViewById(R.id.item_pg_organik);
            tv_organik = itemView.findViewById(R.id.item_persen_organik);
            tv_warn_or = itemView.findViewById(R.id.tv_warn_organik);

            pg_logam = itemView.findViewById(R.id.item_pg_logam);
            tv_logam = itemView.findViewById(R.id.item_persen_logam);
            tv_warn_lg = itemView.findViewById(R.id.tv_warn_logam);
        }
    }
}
