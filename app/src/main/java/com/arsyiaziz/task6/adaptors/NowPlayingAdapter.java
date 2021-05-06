package com.arsyiaziz.task6.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arsyiaziz.task6.R;
import com.arsyiaziz.task6.misc.Constants;
import com.arsyiaziz.task6.misc.OnItemClickListener;
import com.arsyiaziz.task6.models.NowPlayingModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {
    private final List<NowPlayingModel> nowPlaying;
    private final OnItemClickListener<NowPlayingModel> clickListener;

    public NowPlayingAdapter(List<NowPlayingModel> nowPlaying, OnItemClickListener<NowPlayingModel> clickListener) {
        this.nowPlaying = nowPlaying;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.television_item, parent, false);
        return new NowPlayingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingAdapter.ViewHolder viewHolder, int position) {
        Glide.with(viewHolder.itemView.getContext())
                .load(Constants.BASE_IMG_URL + nowPlaying.get(position).getImgUrl())
                .into(viewHolder.ivPoster);
        viewHolder.tvTitle.setText(nowPlaying.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return nowPlaying.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ImageView ivPoster;
        private final TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(nowPlaying.get(getBindingAdapterPosition()));
        }
    }
}
