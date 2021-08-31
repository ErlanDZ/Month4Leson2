package com.example.month4leson2.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.month4leson2.data.netWork.onItemClick.OnItemClick;
import com.example.month4leson2.databinding.ItemCharacterBinding;
import com.example.month4leson2.model.Character;

public class CharacterAdapter extends ListAdapter<Character, CharacterAdapter.CharacterViewholder> {


    public OnItemClick onItemClickCharacter;

    public CharacterAdapter() {
        super(new CharacterDiffUtil());
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterAdapter.CharacterViewholder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewholder holder, int position) {
        holder.onBind(getItem(position));
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.onItemClickCharacter = listener;
    }

    public static class CharacterDiffUtil extends DiffUtil.ItemCallback<Character> {

        @Override
        public boolean areItemsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem == newItem;
        }
    }

    class CharacterViewholder extends RecyclerView.ViewHolder {

        private ItemCharacterBinding binding;

        public CharacterViewholder(ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(Character item) {
            binding.txtNameCharacter.setText(item.getName());
            Glide
                    .with(binding.imageCharacter)
                    .load(item.getImage())
                    .into(binding.imageCharacter);

            binding.getRoot().setOnClickListener(v -> {
                onItemClickCharacter.onClickItemListener(item.getId());
            });
        }
    }
}

