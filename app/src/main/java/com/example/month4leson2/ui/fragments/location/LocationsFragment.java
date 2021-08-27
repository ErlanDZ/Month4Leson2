package com.example.month4leson2.ui.fragments.location;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.month4leson2.R;
import com.example.month4leson2.databinding.FragmentLocationsBinding;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.LocationModel;
import com.example.month4leson2.model.RickAndMortyResponse;
import com.example.month4leson2.ui.adapters.LocationAdapter;

import java.util.ArrayList;

public class LocationsFragment extends Fragment {

    private FragmentLocationsBinding  binding;
    private LocationVIewModel vIewModel;
    private LocationAdapter adapter = new LocationAdapter();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setUpRequest();
    }

    private void setUpRequest() {
        if (isNetworkAvailable()){
            vIewModel.fetchLocations().observe(getViewLifecycleOwner(), locationModel -> {
                if (locationModel != null) {
                    adapter.addList(locationModel.getResults());
                }
            });
        }
        else {
            adapter.addList((ArrayList<LocationModel>) vIewModel.getLocation());
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void initialize() {
        vIewModel = new ViewModelProvider(requireActivity()).get(LocationVIewModel.class);
        setUpRecycler();
    }

    private void setUpRecycler() {
        binding.recyclerLocation.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerLocation.setAdapter(adapter);

        adapter.setOnItemClickLocation(position -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(LocationsFragmentDirections.actionLocationsFragmentToLocatonDetailFragment(position)
                            .setId(position));
        });
    }
}