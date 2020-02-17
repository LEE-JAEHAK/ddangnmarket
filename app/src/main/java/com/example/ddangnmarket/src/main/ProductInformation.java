package com.example.ddangnmarket.src.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ddangnmarket.R;

import static java.lang.System.load;

public class ProductInformation extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);

        imageView=findViewById(R.id.product_information_iv);
        textView=findViewById(R.id.product_information_tv_product_name);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String picture = intent.getStringExtra("pic");

        Glide.with(this).load(picture).into(imageView);
        textView.setText(name);
    }
}
