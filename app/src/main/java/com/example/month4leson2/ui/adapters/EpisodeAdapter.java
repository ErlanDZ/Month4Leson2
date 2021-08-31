package com.example.month4leson2.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.month4leson2.data.netWork.onItemClick.OnItemClick;
import com.example.month4leson2.databinding.ItemEpisodeBinding;
import com.example.month4leson2.model.EpisodeModel;

public  class EpisodeAdapter extends ListAdapter<EpisodeModel, EpisodeAdapter.EpisodeViewHolder>{

    public OnItemClick onItemClickEpisode;

    public EpisodeAdapter() {
        super(new EpisodeDiffUtil());
    }
  public static  class EpisodeDiffUtil extends DiffUtil.ItemCallback<EpisodeModel>{

        @Override
        public boolean areItemsTheSame(@NonNull EpisodeModel oldItem, @NonNull EpisodeModel newItem) {
            return  oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull EpisodeModel oldItem, @NonNull EpisodeModel newItem) {
            return oldItem == newItem;
        }
    }

    @NonNull
    @Override
    public EpisodeAdapter.EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodeAdapter.EpisodeViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.EpisodeViewHolder holder, int position) {
        holder.onBind(getItem(position));
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
                onItemClickEpisode.onClickItemListener(item.getId());
            });
        }
    }

    public void setOnItemClickEpisode(OnItemClick episode){
        this.onItemClickEpisode = episode;
    }
}
