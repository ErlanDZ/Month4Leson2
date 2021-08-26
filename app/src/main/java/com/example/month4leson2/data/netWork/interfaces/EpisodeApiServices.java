package com.example.month4leson2.data.netWork.interfaces;

import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EpisodeApiServices {
    @GET("api/episode")
    Call<RickAndMortyResponse<EpisodeModel>> fetchEpisode();

    @GET("api/episode/{id}")
    Call <EpisodeModel> fetchEpisode(
            @Path("id") int id
    );
}
