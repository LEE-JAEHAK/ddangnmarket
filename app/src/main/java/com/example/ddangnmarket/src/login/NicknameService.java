package com.example.ddangnmarket.src.login;

import com.example.ddangnmarket.src.login.interfaces.NicknameActivityView;
import com.example.ddangnmarket.src.login.interfaces.NicknameRetrofitInterface;
import com.example.ddangnmarket.src.login.models.LoginResponse;
import com.example.ddangnmarket.src.login.models.RequestNickname;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ddangnmarket.src.ApplicationClass.getRetrofit;

public class NicknameService {
    private final NicknameActivityView mNicknameActivityView;

    NicknameService(final NicknameActivityView nocknameActivityView) {
        this.mNicknameActivityView = nocknameActivityView;
    }

    void postNickname(RequestNickname requestNickname) {
        final NicknameRetrofitInterface nicknameRetrofitInterface = getRetrofit().create(NicknameRetrofitInterface.class);
        nicknameRetrofitInterface.postJoin(requestNickname).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                mNicknameActivityView.validateNicknameSuccess(loginResponse.getIsSuccess(), loginResponse.getCode(), loginResponse.getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mNicknameActivityView.validateNicknameFailure();
            }
        });
    }
}
