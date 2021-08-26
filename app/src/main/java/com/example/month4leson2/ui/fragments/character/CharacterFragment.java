package com.example.month4leson2.ui.fragments.character;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.month4leson2.R;
import com.example.month4leson2.ui.adapters.CharacterAdapter;
import com.example.month4leson2.databinding.FragmentCharacterBinding;


public class CharacterFragment extends Fragment {
    private FragmentCharacterBinding binding;
    private CharacterAdapter adapter = new CharacterAdapter();
    private  CharacterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setUpRequest();
    }

    private void setUpRequest() {
        viewModel.techCharacters().observe(getViewLifecycleOwner(), characters -> {
            adapter.addList(characters.getResults());
        });
    }

    private void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        setUpRecycler();
    }

    private void setUpRecycler() {
        binding.recyclerCharacter.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerCharacter.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(position)
                            .setGetId(position));
        });
    }
}