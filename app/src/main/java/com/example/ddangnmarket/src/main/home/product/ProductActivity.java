package com.example.ddangnmarket.src.main.home.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.location.LocationAdapter;
import com.example.ddangnmarket.src.main.home.product.interfaces.ProductActivityView;
import com.example.ddangnmarket.src.main.home.product.models.ProductImageResponse;
import com.example.ddangnmarket.src.main.home.product.models.Result;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

import static com.example.ddangnmarket.src.main.home.ItemsAdapter.moneyFormatToWon;

public class ProductActivity extends BaseActivity implements ProductActivityView {

    private ArrayList<String> mImageList;
    ImageView mImageView;
    ImageButton mBack, mShare, mMore;
    TextView mTvNickname, mTvAddress, mTvManner, mTvtitle, mTvCategories, mTvReroll, mTvText, mTvChat, mTvFavorite, mTvHits, mTvPrice;
    int productNo;
    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;
    CircleIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);

        mImageView = findViewById(R.id.product_information_iv);
        mTvNickname = findViewById(R.id.product_information_tv_nickname);
        mTvAddress = findViewById(R.id.product_information_tv_address);
        mTvManner = findViewById(R.id.product_information_tv_manner);
        mTvtitle = findViewById(R.id.product_information_tv_title);
        mTvCategories = findViewById(R.id.product_information_tv_categories);
        mTvReroll = findViewById(R.id.product_information_tv_reroll);
        mTvText = findViewById(R.id.product_information_tv_text);
        mTvChat = findViewById(R.id.product_information_tv_chat);
        mTvFavorite = findViewById(R.id.product_information_tv_favorite);
        mTvHits = findViewById(R.id.product_information_tv_hits);
        mTvPrice = findViewById(R.id.product_information_tv_price);

        mBack = findViewById(R.id.product_information_iv_back);
        mShare = findViewById(R.id.product_information_iv_share);
        mMore = findViewById(R.id.product_information_iv_more);

        Intent intent = getIntent();
        productNo = intent.getIntExtra("productNo", 1);

        //여기서 부터 뷰페이저
        mImageList = new ArrayList();
        //mImageList.add("https://post-phinf.pstatic.net/MjAxODAzMDVfMTEy/MDAxNTIwMjIyODUzNzI4.m13JYsDImfZxkmjJxMGmYe8lKhYxSExyDqdH66C-_i0g.79FPvMvtpW0I0CaqBRn3D7pNyECV2RLxoYEJ3-uiROsg.JPEG/GettyImages-jv11000346.jpg?type=w800_q75");
        //mImageList.add("https://post-phinf.pstatic.net/MjAxNzExMTZfMTQ5/MDAxNTEwNzYyNTM1ODg4.pnYeiJCMETHQSAe0LQLzAsCpHkNPzozKL-7JBiNVtM8g.u30yTLuF5o1plN59Gp1-kAR6D8QU3PsmSjJffVxWk3Ug.JPEG/%EB%B0%94%EB%82%98%EB%82%981.jpg?type=w1200");
        //mImageList.add("https://firebasestorage.googleapis.com/v0/b/ddangnmarket.appspot.com/o/grape.png?alt=media&token=84813ae2-4cb1-4aa6-becf-ee91371813ca");

        mViewPager = findViewById(R.id.product_information_viewPager);
        mViewPagerAdapter = new ViewPagerAdapter(this,mImageList);
        mViewPager.setAdapter(mViewPagerAdapter);

        //상단바
        mBack.setImageResource(R.drawable.home_as_up);
        mBack.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
        mShare.setImageResource(R.drawable.ic_share_outline_24);
        mShare.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
        mMore.setImageResource(R.drawable.icon_ads_more);
        mMore.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);

        //인디케이터
        mIndicator = findViewById(R.id.product_information_indicator);


        getProduct(productNo);
    }

    public void productInformationOnClick(View view) {
        switch (view.getId()) {
            case R.id.product_information_iv_back:
                onBackPressed();
                break;
            case R.id.product_information_iv_share:
                //share();
                break;
            case R.id.product_information_iv_more:
                // more();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getProduct(int productNo) {
        final ProductService productService = new ProductService(this);
        productService.getProduct(productNo);

        productService.getProductImage(productNo);
    }

    @Override
    public void validateProductSuccess(boolean isSuccess, int code, String message, Result result) {
        if (isSuccess) {
            if (code == 100) {
                showCustomToast(message);

                mTvNickname.setText(result.getId());
                mTvAddress.setText(result.getAddress());
                mTvManner.setText(result.getManner() + "°C");
                mTvtitle.setText(result.getTitle());
                mTvCategories.setText(result.getCategories());
                mTvReroll.setText(" · 끌올 " + result.getReroll() + "분 전");
                mTvText.setText(result.getText());
                mTvChat.setText("채팅 " + result.getChat() + "개");
                mTvFavorite.setText(" · 관심 " + result.getFavorite() + "개");
                mTvHits.setText(" · 조회 " + result.getHits() + "");
                String mPrice = moneyFormatToWon(result.getPrice());
                mTvPrice.setText(mPrice + "원");
            } else showCustomToast(message);
        }
    }

    @Override
    public void validateProductFailure(String message) {
        showCustomToast(message);
    }

    @Override
    public void validateProductImageSuccess(boolean isSuccess, int code, String message, ArrayList<ProductImageResponse.Result> resultArrayList) {
        showCustomToast(message);

        mImageList.clear();
        for (int i = 0; i < resultArrayList.size(); i++) {
            mImageList.add(resultArrayList.get(i).getImageUrl());
        }
        mViewPagerAdapter.notifyDataSetChanged();
        mIndicator.setViewPager(mViewPager);
    }

    @Override
    public void validateProductImageFailure(String message) {
        showCustomToast(message);
    }
}
