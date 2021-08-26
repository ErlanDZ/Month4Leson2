package com.example.month4leson2.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.month4leson2.databinding.ItemEpisodeBinding;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.ui.fragments.episode.OnItemClickEpisode;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {
     ArrayList<EpisodeModel> list = new ArrayList<>();
    public OnItemClickEpisode onItemClickEpisode;

    @NonNull
    @Override
    public EpisodeAdapter.EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodeViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.EpisodeViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(ArrayList<EpisodeModel> lissst) {
        this.list = lissst;
        notifyDataSetChanged();
    }

    public  class EpisodeViewHolder extends RecyclerView.ViewHolder {
        private ItemEpisodeBinding binding;
        public EpisodeViewHolder(@NonNull ItemEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        private void onBind (EpisodeModel item){
            binding.txtNameEpisode.setText(item.getName());
            binding.txtEpisodeEpisode.setText(item.getEpisode());

            binding.getRoot().setOnClickListener(v -> {
             onItemClickEpisode.onItemClickEpisode(item.getId());
            });
        }
    }

    public void setOnItemClickEpisode(OnItemClickEpisode episode){
        this.onItemClickEpisode = episode;
    }
}
