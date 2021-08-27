package com.example.month4leson2.data.netWork.apiservice;

import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharecterApiServise {
    @GET("api/character")
    Call <RickAndMortyResponse<Character>> fetchCharacters ();

    @GET("api/character/{id}")
    Call <Character> fetchCharacter(
            @Path("id") int id
    );
}
