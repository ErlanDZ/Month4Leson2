package com.example.month4leson2.ui.adapters;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.month4leson2.databinding.ItemCharacterBinding;
import com.example.month4leson2.ui.fragments.character.OnItemClickCharacter;
import com.example.month4leson2.model.Character;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewholder> {

    private ArrayList<Character> list = new ArrayList<>();
    public OnItemClickCharacter onItemClickCharacter;


    @NonNull
    @Override
    public CharacterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewholder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewholder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(ArrayList<Character> list) {
        this.list = list;
        notifyDataSetChanged();
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
                onItemClickCharacter.onItemClickCher(item.getId());
            });
        }
    }

    public void setOnItemClickListener(OnItemClickCharacter listener) {
        this.onItemClickCharacter = listener;
    }
}
