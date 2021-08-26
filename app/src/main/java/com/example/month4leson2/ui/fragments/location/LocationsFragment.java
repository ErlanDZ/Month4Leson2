package com.example.month4leson2.ui.fragments.location;

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
import com.example.month4leson2.model.LocationModel;
import com.example.month4leson2.model.RickAndMortyResponse;
import com.example.month4leson2.ui.adapters.LocationAdapter;

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
        vIewModel.fetchLocations().observe(getViewLifecycleOwner(), locationModel -> {
            adapter.addList(locationModel.getResults());
        });
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