package com.example.ddangnmarket.src.location.interfaces;

import com.example.ddangnmarket.src.location.models.Result;

import java.util.ArrayList;

public interface LocationActivityView {

    void validateLocationSuccess(boolean isSuccess, int code, String message, ArrayList<Result> results);

    void validateLocationFailure();

}
