package com.example.month4leson2.ui.fragments.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.month4leson2.App;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.LocationModel;
import com.example.month4leson2.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationVIewModel extends ViewModel {
    public MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocations() {
        MutableLiveData<RickAndMortyResponse<LocationModel>> data = new MutableLiveData<>();
        App.locationApiServise.fetchLocation().enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationModel>> call, Response<RickAndMortyResponse<LocationModel>> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<LocationModel>> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<LocationModel> fetchLocation(int id) {
        MutableLiveData<LocationModel> data = new MutableLiveData<>();
        App.locationApiServise.fetchLocationCALL(id).enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
