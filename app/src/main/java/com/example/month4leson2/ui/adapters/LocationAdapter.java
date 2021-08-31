package com.example.month4leson2.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.month4leson2.data.netWork.onItemClick.OnItemClick;
import com.example.month4leson2.databinding.ItemLocationBinding;
import com.example.month4leson2.model.LocationModel;

public class LocationAdapter extends ListAdapter<LocationModel, LocationAdapter.LocationViewHolder> {

    public OnItemClick onItemClickLocation;

    public LocationAdapter() {
        super(new LocationDiffUtil());
    }

    @NonNull
    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationAdapter.LocationViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocationViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    public void setOnItemClickLocation(OnItemClick location) {
        this.onItemClickLocation = location;
    }

    public static class LocationDiffUtil extends DiffUtil.ItemCallback<LocationModel> {

        @Override
        public boolean areItemsTheSame(@NonNull LocationModel oldItem, @NonNull LocationModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull LocationModel oldItem, @NonNull LocationModel newItem) {
            return oldItem == newItem;
        }
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        private ItemLocationBinding binding;

        public LocationViewHolder(@NonNull ItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(LocationModel item) {
            binding.txtNameLocation.setText(item.getName());
            binding.txtTypeLocation.setText(item.getType());
            binding.txtDimensionLocation.setText(item.getDimension());

            binding.getRoot().setOnClickListener(v -> {
                onItemClickLocation.onClickItemListener(item.getId());
            });
        }
    }
}

