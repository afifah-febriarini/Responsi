package com.aryanadewi_tugaspraktikum.responsi_17030033_17030035.recyclerview_connect_api.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.aryanadewi_tugaspraktikum.responsi_17030033_17030035.R;
import com.aryanadewi_tugaspraktikum.responsi_17030033_17030035.recyclerview_connect_api.model.Result;

import java.time.Instant;
import java.time.temporal.TemporalAdjuster;
import java.util.List;

import static com.aryanadewi_tugaspraktikum.responsi_17030033_17030035.R.*;

public class MusicPopularAdapter extends  RecyclerView.Adapter<MusicPopularAdapter.MusicPopularViewHolder> {
    private List<Result> results;
    private int columnItem;
    private Context context;
    private Instant Glide;

    public MusicPopularAdapter(List<Result> results, int columnItem, Context context) {
        this.results = results;
        this.columnItem = columnItem;
        this.context = context;
    }


    @NonNull
    @Override
    public MusicPopularAdapter.MusicPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(columnItem, parent, false);
        return new MusicPopularViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MusicPopularAdapter.MusicPopularViewHolder holder, int position) {
        Glide.with((TemporalAdjuster) context).load("http://image.tmdb.org/t/p/w185/" + results
                .get(position)
                .getPosterPath())
                .override(200, 200)
                .into(holder.ivPosterPath);
        holder.tvTitle.setText(results.get(position).getTitle());
        holder.tvOverview.setText(results.get(position).getOverview());
        holder.tvReleaseDate.setText(results.get(position).getReleaseDate());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class MusicPopularViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView tvTitle, tvOverview, tvReleaseDate;
        ImageView ivPosterPath;

        public MusicPopularViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(id.ll_music_popular);
            tvTitle = itemView.findViewById(id.title);
            tvOverview = itemView.findViewById(id.subtitle);
            tvReleaseDate = itemView.findViewById(id.description);
            ivPosterPath = itemView.findViewById(id.music_poster_path);
        }
    }
}
