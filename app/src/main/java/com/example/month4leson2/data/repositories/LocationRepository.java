package com.example.month4leson2.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.month4leson2.App;
import com.example.month4leson2.model.LocationModel;
import com.example.month4leson2.model.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {
    public MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocations(int page) {
        MutableLiveData<RickAndMortyResponse<LocationModel>> data = new MutableLiveData<>();
        App.locationApiServise.fetchLocation(page).enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationModel>> call, Response<RickAndMortyResponse<LocationModel>> response) {
                if (response.body() != null) {
                    App.locationDao.insertAll(response.body().getResults());
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<LocationModel>> call, Throwable t) {
                data.setValue(null);
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
    public List<LocationModel> getLocation(){
        return App.locationDao.getAll();
    }
}
