package com.example.month4leson2.ui.fragments.episode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.month4leson2.R;
import com.example.month4leson2.databinding.FragmentEpisodeBinding;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.RickAndMortyResponse;
import com.example.month4leson2.ui.adapters.EpisodeAdapter;
import com.example.month4leson2.ui.fragments.character.CharacterFragmentDirections;


public class EpisodeFragment extends Fragment {

    private FragmentEpisodeBinding binding;
    private EpisodeViewModel viewModel;
    private EpisodeAdapter adapter = new EpisodeAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setUpRequest();
    }

    private void setUpRequest() {
        viewModel.fetchEpisode().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onChanged(RickAndMortyResponse<EpisodeModel> episodeModelRickAndMortyResponse) {
                adapter.addList(episodeModelRickAndMortyResponse.getResults());
            }
        });
    }

    private void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        setUpRecycler();
    }

    private void setUpRecycler() {
        binding.recyclerEpisode.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerEpisode.setAdapter(adapter);

        adapter.setOnItemClickEpisode(position -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(position)
                            .setId(position));
        });
    }
}