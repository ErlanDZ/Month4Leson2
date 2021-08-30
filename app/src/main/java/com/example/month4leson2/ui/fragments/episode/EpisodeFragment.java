package com.example.month4leson2.ui.fragments.episode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.month4leson2.R;
import com.example.month4leson2.base.BaseFragment;
import com.example.month4leson2.databinding.FragmentEpisodeBinding;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.RickAndMortyResponse;
import com.example.month4leson2.ui.adapters.EpisodeAdapter;

import java.util.ArrayList;


public class EpisodeFragment extends BaseFragment<EpisodeViewModel, FragmentEpisodeBinding> {

    private FragmentEpisodeBinding binding;
    private EpisodeViewModel viewModel;
    private EpisodeAdapter adapter = new EpisodeAdapter();
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        super.initialize();
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        setUpRecycler();
    }

    private int visibleItemCount;
    private  int totalItemCount;
    private int pastVisiblesItems;

    public void setUpRequest() {
        if (isNetworkAvailable()) {
            viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), episodeModelRickAndMortyResponse -> {
                if (episodeModelRickAndMortyResponse != null) {
                    adapter.addList(episodeModelRickAndMortyResponse.getResults());
                }
            });
        } else {
            adapter.addList((ArrayList<EpisodeModel>) viewModel.getEpisode());
        }

        binding.recyclerEpisode.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy >0){
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount){
                        viewModel.page++;
                        viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), episode -> {
                            adapter.addList(episode.getResults());
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
        binding.recyclerEpisode.setLayoutManager(linearLayoutManager);
        binding.recyclerEpisode.setAdapter(adapter);

        adapter.setOnItemClickEpisode(position -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(position)
                            .setId(position));
        });
    }
}