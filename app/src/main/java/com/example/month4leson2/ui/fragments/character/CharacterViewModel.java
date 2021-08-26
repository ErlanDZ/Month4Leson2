package com.example.month4leson2.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.month4leson2.App;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {
    public MutableLiveData<RickAndMortyResponse<Character>> techCharacters() {
        MutableLiveData<RickAndMortyResponse<Character>> data = new MutableLiveData<>();
        App.charecterApiServise.fetchCharacters().enqueue(new Callback<RickAndMortyResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Character>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<Character> fetchCharacter(int id) {
        MutableLiveData<Character> data = new MutableLiveData<>();
        App.charecterApiServise.fetchCharacter(id).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
