package com.example.ddangnmarket.src.main.home.product.interfaces;

import com.example.ddangnmarket.src.main.home.product.models.Result;

public interface ProductActivityView {

    void validateProductSuccess(boolean isSuccess, int code, String message, Result result);

    void validateProductFailure(String message);
}
