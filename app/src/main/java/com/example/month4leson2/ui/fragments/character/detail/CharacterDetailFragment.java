package com.example.month4leson2.ui.fragments.character.detail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.month4leson2.base.BaseFragment;
import com.example.month4leson2.databinding.FragmentCharacterDetailBinding;
import com.example.month4leson2.ui.fragments.character.CharacterViewModel;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.RickAndMortyResponse;

public class CharacterDetailFragment extends BaseFragment<CharacterViewModel, FragmentCharacterDetailBinding> {
    private FragmentCharacterDetailBinding binding;
    private CharacterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        super.initialize();
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
    }

    @Override
    protected void getDate() {
        super.getDate();
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