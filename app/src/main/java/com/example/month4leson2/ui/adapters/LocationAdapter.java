package com.example.month4leson2.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.month4leson2.databinding.ItemLocationBinding;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.LocationModel;
import com.example.month4leson2.ui.fragments.episode.OnItemClickEpisode;
import com.example.month4leson2.ui.fragments.location.OnItemClickLocation;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocftionViewHolder> {

    ArrayList<LocationModel> list = new ArrayList<>();
      public OnItemClickLocation onItemClickLocation ;

    @NonNull
    @Override
    public LocationAdapter.LocftionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocftionViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocftionViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(ArrayList<LocationModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class LocftionViewHolder extends RecyclerView.ViewHolder {
        private ItemLocationBinding binding;

        public LocftionViewHolder(@NonNull ItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(LocationModel item) {
            binding.txtNameLocation.setText(item.getName());
            binding.txtTypeLocation.setText(item.getType());
            binding.txtDimensionLocation.setText(item.getDimension());

            binding.getRoot().setOnClickListener(v -> {
                onItemClickLocation.onClickItemLocation(item.getId());
            });
        }
    }
    public void setOnItemClickLocation(OnItemClickLocation location){
        this.onItemClickLocation = location;
    }
}
