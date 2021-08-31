package com.example.month4leson2.ui.fragments.location;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.month4leson2.R;
import com.example.month4leson2.base.BaseFragment;
import com.example.month4leson2.databinding.FragmentLocationsBinding;
import com.example.month4leson2.model.LocationModel;
import com.example.month4leson2.ui.adapters.LocationAdapter;

import java.util.ArrayList;

public class LocationsFragment extends BaseFragment<LocationVIewModel, FragmentLocationsBinding> {

    private FragmentLocationsBinding binding;
    private LocationAdapter adapter = new LocationAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(LocationVIewModel.class);
    }

    public void setUpRequest() {
        if (isNetworkAvailable()) {
            viewModel.fetchLocations().observe(getViewLifecycleOwner(), locationModel -> {
                if (locationModel != null) {
                    adapter.submitList(locationModel.getResults());
                }
            });
        } else {
            adapter.submitList((ArrayList<LocationModel>) viewModel.getLocation());
        }
        binding.recyclerLocation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        viewModel.page++;
                        viewModel.fetchLocations().observe(getViewLifecycleOwner(), location -> {
                            if (location != null)
                                adapter.submitList(location.getResults());
                        });
                    }
                }

            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void setUpRecycler() {
        super.setUpRecycler();
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerLocation.setLayoutManager(linearLayoutManager);
        binding.recyclerLocation.setAdapter(adapter);

        adapter.setOnItemClickLocation(position -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(LocationsFragmentDirections.actionLocationsFragmentToLocatonDetailFragment(position)
                            .setId(position));
        });
    }
}