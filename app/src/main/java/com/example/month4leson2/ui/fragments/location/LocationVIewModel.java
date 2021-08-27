package com.example.month4leson2.ui.fragments.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.month4leson2.App;
import com.example.month4leson2.data.repositories.CharacterRepository;
import com.example.month4leson2.data.repositories.EpisodeRepository;
import com.example.month4leson2.data.repositories.LocationRepository;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.LocationModel;
import com.example.month4leson2.model.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationVIewModel extends ViewModel {
    private final LocationRepository  locationRepository= new LocationRepository();

    public MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocations() {
        return locationRepository.fetchLocations();
    }

    public   MutableLiveData<LocationModel> fetchLocation(int id) {
        return locationRepository.fetchLocation(id);
    }

    List<LocationModel> getLocation() {
        return locationRepository.getLocation();
    }
}
