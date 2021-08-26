package com.example.month4leson2.ui.fragments.character.detail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.month4leson2.databinding.FragmentCharacterDetailBinding;
import com.example.month4leson2.ui.fragments.character.CharacterViewModel;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.RickAndMortyResponse;

public class CharacterDetailFragment extends Fragment {
    private FragmentCharacterDetailBinding binding;
    private CharacterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
        getDate();
    }

    private void initialization() {
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
    }

    private void getDate() {
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(getArguments())
                .getGetId()).observe(getViewLifecycleOwner(), character -> {
            binding.txtNameCharacterDetail.setText(character.getName());
            binding.txtStatusDetail.setText(character.getStatus());
            Glide.with(binding.imageCharacterDetail)
                    .load(character.getImage())
                    .into(binding.imageCharacterDetail);
        });
    }
}