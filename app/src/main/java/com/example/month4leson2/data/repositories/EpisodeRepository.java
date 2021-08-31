package com.example.month4leson2.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.month4leson2.App;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository {
    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisodes(int page) {
        MutableLiveData<RickAndMortyResponse<EpisodeModel>> data = new MutableLiveData<>();
        App.episodeApiServices.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<EpisodeModel>> call, Response<RickAndMortyResponse<EpisodeModel>> response) {
                if (response.body() != null) {
                    App.episodeDao.insertAll(response.body().getResults());
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<EpisodeModel>> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<EpisodeModel> fetchEpisode(int id) {
        MutableLiveData<EpisodeModel> data = new MutableLiveData<>();
        App.episodeApiServices.fetchEpisode(id).enqueue(new Callback<EpisodeModel>() {
            @Override
            public void onResponse(Call<EpisodeModel> call, Response<EpisodeModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<EpisodeModel> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public List<EpisodeModel> getEpisode() {
        return App.episodeDao.getAll();
    }
}
