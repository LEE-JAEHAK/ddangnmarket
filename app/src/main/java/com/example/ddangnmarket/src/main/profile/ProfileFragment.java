package com.example.ddangnmarket.src.main.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.login.LoginActivity;
import com.example.ddangnmarket.src.main.MainActivity;

import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;

public class ProfileFragment extends Fragment {
    MainActivity activity;
    LinearLayout mBtnLogin, mTvSetting;
    TextView mTvNickname, mTvLocation;
    ImageView mIvSetting;
    int mLocationNo;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mBtnLogin = view.findViewById(R.id.profile_btn_login);
        mTvNickname = view.findViewById(R.id.profile_tv_nickname);
        mTvLocation = view.findViewById(R.id.profile_tv_location);
        mTvSetting = view.findViewById(R.id.profile_tv_setting);
        mIvSetting = view.findViewById(R.id.profile_iv_setting);

        mIvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveSettingActivity();
            }
        });
        mTvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveSettingActivity();
            }
        });

        //SharedPreferences sharedPreferences = activity.getSharedPreferences("X-ACCESS-TOKEN", getContext().MODE_PRIVATE);
        String jwt = sSharedPreferences.getString("X-ACCESS-TOKEN", "");
        //mLocationNo = sharedPreferences.getInt("locationNo", 1);
        String nickname = sSharedPreferences.getString("nickname", "");
        String dong = sSharedPreferences.getString("dong", "");

        if (jwt.equals("")) {
            mBtnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveLoginActivity();
                }
            });
        } else {
            mBtnLogin.setEnabled(false);
            mTvNickname.setText(nickname);
            mTvLocation.setText(dong);
        }

        return view;
    }

    public void moveLoginActivity() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }

    public void moveSettingActivity() {
        Intent intent = new Intent(activity, SettingActivity.class);
        startActivity(intent);
    }
}
