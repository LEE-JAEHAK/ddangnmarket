package com.example.ddangnmarket.src.nickname.interfaces;

public interface NicknameActivityView {

    void validateNicknameSuccess(boolean isSuccess, int code, String message);

    void validateNicknameFailure();

    void validateJwtSuccess(boolean isSuccess, int code, String message, String jwt);

    void validateJwtFailure();
}
