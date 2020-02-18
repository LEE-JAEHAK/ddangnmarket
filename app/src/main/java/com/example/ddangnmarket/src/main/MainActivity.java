package com.example.ddangnmarket.src.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.main.category.CategoryFragment;
import com.example.ddangnmarket.src.main.chat.ChatFragment;
import com.example.ddangnmarket.src.main.home.HomeFragment;
import com.example.ddangnmarket.src.main.profile.ProfileFragment;
import com.example.ddangnmarket.src.main.writing.WritingFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment mHomeFragment;
    CategoryFragment mCategoryFragment;
    WritingFragment mWritingFragment;
    ChatFragment mChatFragment;
    ProfileFragment mProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHomeFragment = new HomeFragment();
        mCategoryFragment = new CategoryFragment();
        mWritingFragment = new WritingFragment();
        mChatFragment = new ChatFragment();
        mProfileFragment = new ProfileFragment();

        moveHome();
    }

    public void moveHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mHomeFragment).commit();
    }

    public void moveCategory() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mCategoryFragment).commit();
    }

    public void moveWriting() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mWritingFragment).commit();
    }

    public void moveChat() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mChatFragment).commit();
    }

    public void moveProfile() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mProfileFragment).commit();
    }

    public void fragmentOnClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_home:
                moveHome();
                break;
            case R.id.main_btn_category:
                moveCategory();
                break;
            case R.id.main_btn_writing:
                moveWriting();
                break;
            case R.id.main_btn_chat:
                moveChat();
                break;
            case R.id.main_btn_profile:
                moveProfile();
                break;
            default:
                break;
        }
    }
}
