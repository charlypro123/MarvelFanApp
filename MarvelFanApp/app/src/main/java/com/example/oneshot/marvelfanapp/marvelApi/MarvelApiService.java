package com.example.oneshot.marvelfanapp.marvelApi;

import com.example.oneshot.marvelfanapp.models.CharactersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApiService {

    String NAME = "name";
    String API_KEY = "apikey";
    String HASH = "hash";
    String TIMESTAMP = "ts";

    //https://gateway.marvel.com/v1/public/characters?apikey=4c5844fc8e684e8ef3a438dd1bc9cbbf&ts=9&hash=dcce4983c5c7f8fb8af2a9000dfee5c4
    @GET("/v1/public/characters")
    Call<CharactersResponse> obtenerListaCharacters(
            //@Query("name") String name,
            @Query("offset") int offset,
            @Query(API_KEY) String publickey,
            @Query(HASH)String hash,
            @Query(TIMESTAMP)long timestamp



    );
}
