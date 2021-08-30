package com.example.month4leson2.ui.fragments.character;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.month4leson2.R;
import com.example.month4leson2.base.BaseFragment;
import com.example.month4leson2.databinding.FragmentCharacterBinding;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.RickAndMortyResponse;
import com.example.month4leson2.ui.adapters.CharacterAdapter;

import java.util.ArrayList;


public class CharacterFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding> {
    private FragmentCharacterBinding binding;
    private CharacterAdapter adapter = new CharacterAdapter();
    private CharacterViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        super.initialize();
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
    }
    private int visibleItemCount;
    private  int totalItemCount;
    private int pastVisiblesItems;

    @Override
    protected void setUpRequest() {
        super.setUpRequest();
        if (isNetworkAvailable()) {
            viewModel.techCharacters().observe(getViewLifecycleOwner(), characters -> {
                if (characters != null) {
                    adapter.addList(characters.getResults());
                }
            });
        } else {
            adapter.addList((ArrayList<Character>) viewModel.getCharacters());
        }

        binding.recyclerCharacter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy >0){
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount){
                        viewModel.page++;
                        viewModel.techCharacters().observe(getViewLifecycleOwner(), character -> {
                            adapter.addList(character.getResults());
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
        binding.recyclerCharacter.setLayoutManager(linearLayoutManager);
        binding.recyclerCharacter.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(position)
                            .setGetId(position));
        });
    }
}