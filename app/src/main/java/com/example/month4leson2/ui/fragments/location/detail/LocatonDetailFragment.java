package com.example.month4leson2.ui.fragments.location.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.month4leson2.base.BaseFragment;
import com.example.month4leson2.databinding.FragmentLocatonDetailBinding;
import com.example.month4leson2.ui.fragments.location.LocationVIewModel;


public class LocatonDetailFragment extends BaseFragment<LocationVIewModel, FragmentLocatonDetailBinding> {

    private FragmentLocatonDetailBinding binding;
    private LocationVIewModel vIewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocatonDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        super.initialize();
        vIewModel = new ViewModelProvider(requireActivity()).get(LocationVIewModel.class);
    }

    @Override
    protected void getDate() {
        super.getDate();
        vIewModel.fetchLocation(LocatonDetailFragmentArgs.fromBundle(getArguments())
                .getId()).observe(getViewLifecycleOwner(), locationModel -> {
            binding.txtNameLocationDetail.setText(locationModel.getName());
            binding.txtTypeLocationDetail.setText(locationModel.getType());
            binding.txtDimensionLocationDetail.setText(locationModel.getDimension());
        });
    }
}