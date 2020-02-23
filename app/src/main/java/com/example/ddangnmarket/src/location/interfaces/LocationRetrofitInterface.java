package com.example.ddangnmarket.src.location.interfaces;

import com.example.ddangnmarket.src.location.models.LocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationRetrofitInterface {
    @GET("/location")
    Call<LocationResponse> getLocationQuery(
            @Query("address") final String address
    );
}
