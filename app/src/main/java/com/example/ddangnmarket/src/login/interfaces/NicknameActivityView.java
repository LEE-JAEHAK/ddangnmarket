package com.example.ddangnmarket.src.login.interfaces;

public interface NicknameActivityView {

    void validateNicknameSuccess(boolean isSuccess, int code, String message);

    void validateNicknameFailure();
}
