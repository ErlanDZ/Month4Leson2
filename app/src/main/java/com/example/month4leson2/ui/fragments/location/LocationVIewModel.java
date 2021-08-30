package com.example.month4leson2.ui.fragments.location;

import androidx.lifecycle.MutableLiveData;

import com.example.month4leson2.base.BaseViewModel;
import com.example.month4leson2.data.repositories.LocationRepository;
import com.example.month4leson2.model.LocationModel;
import com.example.month4leson2.model.RickAndMortyResponse;

import java.util.List;

public class LocationVIewModel extends BaseViewModel {

    private final LocationRepository locationRepository = new LocationRepository();
    public int page = 1;

    public MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocations() {
        return locationRepository.fetchLocations(page);
    }

    public MutableLiveData<LocationModel> fetchLocation(int id) {
        return locationRepository.fetchLocation(id);
    }

    List<LocationModel> getLocation() {
        return locationRepository.getLocation();
    }
}
