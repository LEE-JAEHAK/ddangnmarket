package com.example.ddangnmarket.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.login.interfaces.NicknameActivityView;
import com.example.ddangnmarket.src.login.models.RequestNickname;
import com.example.ddangnmarket.src.main.MainActivity;

public class NicknameActivity extends BaseActivity implements NicknameActivityView {
    TextView mTvPhoneNumber;
    EditText mEtNickname, mEtLocationNo;
    Button mBtnStart;
    String mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        Intent intent = getIntent();
        mPhoneNumber = intent.getStringExtra("phoneNumber");

        mTvPhoneNumber = findViewById(R.id.nickname_tv_phone_number);
        mTvPhoneNumber.setText(mPhoneNumber);
        mEtNickname = findViewById(R.id.nickname_et_nickname);
        mEtLocationNo = findViewById(R.id.nickname_et_location);
        mBtnStart = findViewById(R.id.nickname_btn_start);
    }

    public void nicknameOnClick(View view) {
        switch (view.getId()) {
            case R.id.nickname_btn_start:
                loginStart();
                break;
            default:
                break;
        }
    }

    public void loginStart() {
        NicknameService nicknameService = new NicknameService(this);
        RequestNickname requestNickname = new RequestNickname();
        requestNickname.setPhoneNum(mPhoneNumber);
        requestNickname.setId(mEtNickname.getText().toString());
        requestNickname.setLocation(Integer.parseInt(mEtLocationNo.getText().toString()));
        nicknameService.postNickname(requestNickname);
    }

    @Override
    public void validateNicknameSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess && code == 100) {
            showCustomToast(message);
            Intent intent = new Intent(NicknameActivity.this, MainActivity.class);
            intent.putExtra("flag","login");
            intent.putExtra("nickname",mEtNickname.getText().toString());
            startActivity(intent);
            finish();
        }
        else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateNicknameFailure() {
        showCustomToast("validateNicknameFailure");
    }
}
