package com.example.ddangnmarket.src.login.interfaces;

import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.login.models.RequestMessage;
import com.example.ddangnmarket.src.login.models.RequestNickname;
import com.example.ddangnmarket.src.login.models.RequestPhoneCert;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @POST("/message")
    Call<LoginResponse> postPhone(@Body RequestMessage params);

    @POST("/phonecert")
    Call<LoginResponse> postCert(@Body RequestPhoneCert params);
}
