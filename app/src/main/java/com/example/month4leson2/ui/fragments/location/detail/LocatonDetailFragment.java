package com.example.month4leson2.ui.fragments.location.detail;

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
import com.example.month4leson2.databinding.FragmentEpisodeDetailBinding;
import com.example.month4leson2.databinding.FragmentLocatonDetailBinding;
import com.example.month4leson2.model.LocationModel;
import com.example.month4leson2.ui.fragments.episode.EpisodeViewModel;
import com.example.month4leson2.ui.fragments.location.LocationVIewModel;


public class LocatonDetailFragment extends Fragment {

    private FragmentLocatonDetailBinding binding;
    private LocationVIewModel vIewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocatonDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
        getDate();
    }

    private void getDate() {
        vIewModel.fetchLocation(LocatonDetailFragmentArgs.fromBundle(getArguments())
                .getId()).observe(getViewLifecycleOwner(), locationModel -> {
                    binding.txtNameLocationDetail.setText(locationModel.getName());
                    binding.txtTypeLocationDetail.setText(locationModel.getType());
                    binding.txtDimensionLocationDetail.setText(locationModel.getDimension());
                });
    }

    private void initialization() {
        vIewModel = new ViewModelProvider(requireActivity()).get(LocationVIewModel.class);
    }
}