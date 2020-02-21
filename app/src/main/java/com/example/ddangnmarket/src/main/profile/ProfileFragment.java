package com.example.ddangnmarket.src.main.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.login.LoginActivity;
import com.example.ddangnmarket.src.main.MainActivity;

public class ProfileFragment extends Fragment {
    MainActivity activity;
    LinearLayout mBtnLogin;
    TextView mTvNickname;

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
        Bundle bundle = this.getArguments();
        String flag = bundle.getString("flag");
        String nickname = bundle.getString("nickname");
        System.out.println(flag);

        if("login".equals(flag)){
            mBtnLogin.setEnabled(false);
            mTvNickname.setText(nickname);
        }else {
            mBtnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveLoginActivity();
                }
            });
        }
        return view;
    }

    public void moveLoginActivity() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }
}
