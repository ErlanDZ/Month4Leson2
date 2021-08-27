package com.example.month4leson2.data.netWork.apiservice;

import com.example.month4leson2.model.LocationModel;
import com.example.month4leson2.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocationApiServise {
    @GET("api/location")
    Call<RickAndMortyResponse<LocationModel>> fetchLocation ();

    @GET("api/location/{id}")
    Call<LocationModel> fetchLocationCALL(
            @Path("id") int id
    );
}
