package com.example.ddangnmarket.src.main.home.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ddangnmarket.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class ProductInformation extends AppCompatActivity {

    private ArrayList<String> mImageList;
    ImageView mImageView;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);

        mImageView = findViewById(R.id.product_information_iv);
        mTextView = findViewById(R.id.product_information_tv_product_name);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String picture = intent.getStringExtra("pic");

        mImageList = new ArrayList();
        mImageList.add(picture);
        mImageList.add("https://post-phinf.pstatic.net/MjAxODAzMDVfMTEy/MDAxNTIwMjIyODUzNzI4.m13JYsDImfZxkmjJxMGmYe8lKhYxSExyDqdH66C-_i0g.79FPvMvtpW0I0CaqBRn3D7pNyECV2RLxoYEJ3-uiROsg.JPEG/GettyImages-jv11000346.jpg?type=w800_q75");
        mImageList.add("https://post-phinf.pstatic.net/MjAxNzExMTZfMTQ5/MDAxNTEwNzYyNTM1ODg4.pnYeiJCMETHQSAe0LQLzAsCpHkNPzozKL-7JBiNVtM8g.u30yTLuF5o1plN59Gp1-kAR6D8QU3PsmSjJffVxWk3Ug.JPEG/%EB%B0%94%EB%82%98%EB%82%981.jpg?type=w1200");

        ViewPager viewPager = findViewById(R.id.product_information_viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(this, mImageList));

        mTextView.setText(name);

        CircleIndicator indicator = findViewById(R.id.product_information_indicator);
        indicator.setViewPager(viewPager);
    }

}
