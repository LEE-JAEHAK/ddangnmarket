package com.example.ddangnmarket.src.login.interfaces;

public interface LoginActivityView {
    void validateMessageSuccess(boolean isSuccess, int code, String message);

    void validateMessageFailure();

    void validatePhoneCertSuccess(boolean isSuccess, int code, String message);

    void validatePhoneCertFailure();

    void validateJwtSuccess(boolean isSuccess, int code, String message, String jwt);

    void validateJwtFailure();
}
