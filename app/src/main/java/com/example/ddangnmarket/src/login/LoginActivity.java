package com.example.ddangnmarket.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.login.interfaces.LoginActivityView;
import com.example.ddangnmarket.src.login.models.RequestMessage;
import com.example.ddangnmarket.src.login.models.RequestPhoneCert;
import com.example.ddangnmarket.src.main.MainActivity;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    EditText mEtPhoneNumber, mEtPutCert;
    Button mBtnGetCert, mBtnStart;
    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtPhoneNumber = findViewById(R.id.login_et_phone_number);
        mEtPutCert = findViewById(R.id.login_et_put_cert);
        mBtnGetCert = findViewById(R.id.login_btn_get_cert);
        mBtnStart = findViewById(R.id.login_btn_start);
    }

    public void loginOnClick(View view) {
        switch (view.getId()) {
            case R.id.login_iv_back:
                onBackPressed();
                break;
            case R.id.login_btn_get_cert:
                getCert();
                break;
            case R.id.login_btn_start:
                loginStart();
                break;
            default:
                break;
        }
    }

    public void getCert() {
        LoginService loginService = new LoginService(this);
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setPhoneNum(mEtPhoneNumber.getText().toString());
        loginService.postPhone(requestMessage);
    }

    public void loginStart() {
        LoginService loginService = new LoginService(this);
        RequestPhoneCert requestPhoneCert = new RequestPhoneCert();
        requestPhoneCert.setPhoneNum(mEtPhoneNumber.getText().toString());
        requestPhoneCert.setCertNo(Integer.parseInt(mEtPutCert.getText().toString()));
        loginService.postCert(requestPhoneCert);
    }

    @Override
    public void validateMessageSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess == true && code == 100) {
            showCustomToast(message);
        } else if (isSuccess == false && code == 200) {
            showCustomToast(message);
        }
    }

    @Override
    public void validateMessageFailure() {
        showCustomToast("validateMessageFailure");
    }

    @Override
    public void validatePhoneCertSuccess(boolean isSuccess, int code, String message) {
        System.out.println(isSuccess + " " + code + " " + message);
        if (isSuccess) {
            String phoneNumber = mEtPhoneNumber.getText().toString();
            if (code == 100) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                showCustomToast(message);
                intent.putExtra("flag", "login");
                startActivity(intent);
                finish();
            } else if (code == 101) {
                Intent intent = new Intent(LoginActivity.this, NicknameActivity.class);
                showCustomToast(message);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
                finish();
            }
        } else
            showCustomToast(message);
    }

    @Override
    public void validatePhoneCertFailure() {
        showCustomToast("validatePhoneCertFailure");
    }
}
