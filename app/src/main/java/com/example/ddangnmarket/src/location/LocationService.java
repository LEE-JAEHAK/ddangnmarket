package com.example.ddangnmarket.src.location;

import com.example.ddangnmarket.src.location.interfaces.LocationActivityView;
import com.example.ddangnmarket.src.location.interfaces.LocationRetrofitInterface;
import com.example.ddangnmarket.src.location.models.LocationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class LocationService {
    private final LocationActivityView mLocationActivityView;

    LocationService(final LocationActivityView mLocationActivityView) {
        this.mLocationActivityView = mLocationActivityView;
    }

    void getLocation(String address) {
        final LocationRetrofitInterface locationRetrofitInterface = getRetrofit().create(LocationRetrofitInterface.class);
        locationRetrofitInterface.getLocationQuery(address).enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                final LocationResponse locationResponse = response.body();
                if (locationResponse == null) {
                    mLocationActivityView.validateLocationFailure(response.message());
                    return;
                }

                mLocationActivityView.validateLocationSuccess(locationResponse.getIsSuccess(),locationResponse.getCode(),locationResponse.getMessage(),locationResponse.getResult());
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                mLocationActivityView.validateLocationFailure(t.getMessage());
            }
        });
    }
}
