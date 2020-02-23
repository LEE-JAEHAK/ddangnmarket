package com.example.ddangnmarket.src.location;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.location.interfaces.LocationActivityView;
import com.example.ddangnmarket.src.location.models.Result;
import com.example.ddangnmarket.src.login.LoginActivity;
import com.example.ddangnmarket.src.nickname.NicknameActivity;

import java.util.ArrayList;

public class LocationActivity extends BaseActivity implements LocationActivityView {

    ListView mLvLocationList;
    EditText mEtLocation;
    Button mBtnLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getLocaton("");

        mEtLocation = findViewById(R.id.location_et_search);
        mEtLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                getLocaton(s.toString());
            }
        });

        mBtnLocation = findViewById(R.id.location_btn_search);
        mBtnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocaton("");
            }
        });
    }

    public void getLocaton(String location) {
        final LocationService locationService = new LocationService(this);
        locationService.getLocation(location);
    }

    @Override
    public void validateLocationSuccess(boolean isSuccess, int code, String message, ArrayList<Result> results) {
        hideProgressDialog();
        if (isSuccess) {
            if (code == 100) {
                showCustomToast(message);
                LocationAdapter locationAdapter = new LocationAdapter(results, this);
                mLvLocationList = findViewById(R.id.location_lv);
                mLvLocationList.setAdapter(locationAdapter);
            } else if (code == 101) {
                LocationAdapter locationAdapter = new LocationAdapter(results, this);
                mLvLocationList = findViewById(R.id.location_lv);
                mLvLocationList.setAdapter(locationAdapter);
            }
        } else {
        }
    }

    @Override
    public void validateLocationFailure() {
        showCustomToast("validateLocationFailure");
    }
}
