package com.marvel.app.data.remote;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v1/public/characters")
    Observable<JsonElement> getCharacters(@Query("limit") int limit, @Query("offset") int offset,
                                          @Query("ts") String timestamp, @Query("apikey") String apikey,
                                          @Query("hash") String hashSignature);
}
