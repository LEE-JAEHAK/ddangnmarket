package com.example.ddangnmarket.src.location;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.ddangnmarket.R;

import java.util.ArrayList;

public class LocationSettingActivity extends AppCompatActivity {

    ArrayList<Location> mLocationArrayList = new ArrayList<>();
    ListView mLvLocationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_setting);

        LocationAdapter locationAdapter = new LocationAdapter(mLocationArrayList,this);
        mLvLocationList = findViewById(R.id.location_lv);

        for (int i = 0; i < 30; i++) {
            mLocationArrayList.add(new Location("인천시", "서구", "청라" + (i + 1) + "동"));
        }

        mLvLocationList.setAdapter(locationAdapter);
    }
}
