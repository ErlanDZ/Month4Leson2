package com.example.month4leson2.ui.fragments.episode.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.month4leson2.R;
import com.example.month4leson2.databinding.FragmentCharacterDetailBinding;
import com.example.month4leson2.databinding.FragmentEpisodeBinding;
import com.example.month4leson2.databinding.FragmentEpisodeDetailBinding;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.ui.fragments.episode.EpisodeViewModel;

public class EpisodeDetailFragment extends Fragment {
    private FragmentEpisodeDetailBinding binding;
    private EpisodeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
        getDate();
    }

    private void getDate() {
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(getArguments())
                .getId()).observe(getViewLifecycleOwner(), model -> {
                    binding.txtNameEpisodeDetail.setText(model.getName());
                    binding.txtEpisodeEpisodeDetail.setText(model.getEpisode());
                });
    }

    private void initialization() {
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
    }
}