package com.example.bakingappclone.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingappclone.R;
import com.example.bakingappclone.models.Step;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.ArrayList;
import java.util.List;

public class StepAdapter extends RecyclerView.Adapter {

    List<Step> stepList = new ArrayList<>();

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item_layout, parent, false);
        return new StepItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StepItemViewHolder) holder).bindData(stepList.get(position));
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class StepItemViewHolder extends RecyclerView.ViewHolder {

        private TextView numberTextView;
        private TextView nameTextView;
        private TextView descriptionTextView;
        private ExoPlayer player;
        private StyledPlayerView playerView;

        public StepItemViewHolder(@NonNull View itemView) {
            super(itemView);

            numberTextView = itemView.findViewById(R.id.tv_step_number);
            nameTextView = itemView.findViewById(R.id.tv_step_name);
            descriptionTextView = itemView.findViewById(R.id.tv_step_description);

            playerView = itemView.findViewById(R.id.step_video);
        }

        public void bindData(Step step) {
            if (step.getId() == 0) {
                numberTextView.setText("!");
            } else {
                numberTextView.setText(String.valueOf(step.getId()));
            }

            nameTextView.setText(step.getShortDescription());
            descriptionTextView.setText(step.getDescription());

            if (step.getVideoURL() != null) {
                player = new ExoPlayer.Builder(this.itemView.getContext()).build();
                MediaItem mediaItem = MediaItem.fromUri(step.getVideoURL());
                playerView.setPlayer(player);
                player.setMediaItem(mediaItem);
                player.prepare();
            }

        }
    }
}
