package com.example.month4leson2.ui.fragments.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.month4leson2.App;
import com.example.month4leson2.data.netWork.interfaces.EpisodeApiServices;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends ViewModel {
    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisode() {
        MutableLiveData<RickAndMortyResponse<EpisodeModel>> data = new MutableLiveData<>();
        App.episodeApiServices.fetchEpisode().enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<EpisodeModel>> call, Response<RickAndMortyResponse<EpisodeModel>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<EpisodeModel>> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }
    public MutableLiveData<EpisodeModel> fetchEpisode(int id){
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
        return  data;
    }
}
