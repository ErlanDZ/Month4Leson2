package com.example.month4leson2.ui.fragments.episode.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.month4leson2.base.BaseFragment;
import com.example.month4leson2.databinding.FragmentEpisodeDetailBinding;
import com.example.month4leson2.ui.fragments.episode.EpisodeViewModel;

public class EpisodeDetailFragment extends BaseFragment<EpisodeViewModel, FragmentEpisodeDetailBinding> {
    private FragmentEpisodeDetailBinding binding;
    private EpisodeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        super.initialize();
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
    }

    @Override
    protected void getDate() {
        super.getDate();
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(getArguments())
                .getId()).observe(getViewLifecycleOwner(), model -> {
            binding.txtNameEpisodeDetail.setText(model.getName());
            binding.txtEpisodeEpisodeDetail.setText(model.getEpisode());
        });
    }
}