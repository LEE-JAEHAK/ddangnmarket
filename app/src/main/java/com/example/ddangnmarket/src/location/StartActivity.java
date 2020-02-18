package com.example.ddangnmarket.src.location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ddangnmarket.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void intentToLocationActivity(){
        Intent intent = new Intent(StartActivity.this, LocationSettingActivity.class);
        startActivity(intent);
        finish();
    }

    public void startOnClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn_set_location:
                intentToLocationActivity();
                break;
            default:
                break;
        }
    }
}
