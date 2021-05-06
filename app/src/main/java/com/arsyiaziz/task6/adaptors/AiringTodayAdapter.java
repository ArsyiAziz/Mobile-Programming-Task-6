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
import com.arsyiaziz.task6.models.AiringTodayModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class AiringTodayAdapter extends RecyclerView.Adapter<AiringTodayAdapter.ViewHolder> {
    private final List<AiringTodayModel> airingToday;
    private final OnItemClickListener<AiringTodayModel> clickListener;

    public AiringTodayAdapter(List<AiringTodayModel> airingToday, OnItemClickListener<AiringTodayModel> clickListener) {
        this.airingToday = airingToday;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.television_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Glide.with(viewHolder.itemView.getContext())
                .load(Constants.BASE_IMG_URL + airingToday.get(position).getImgUrl())
                .into(viewHolder.ivPoster);
        viewHolder.tvTitle.setText(airingToday.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return airingToday.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            clickListener.onClick(airingToday.get(getBindingAdapterPosition()));
        }
    }
}
