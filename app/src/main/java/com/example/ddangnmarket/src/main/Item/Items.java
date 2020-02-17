package com.example.ddangnmarket.src.main.Item;

import android.graphics.drawable.Drawable;

public class Items {
    private String name, gu, dong;
    private int price;
    private Drawable product;

    public Items(String name, String gu, String dong, int price, Drawable product) {
        this.name = name;
        this.gu = gu;
        this.dong = dong;
        this.price = price;
        this.product = product;
    }
    public String getName() {
        return name;
    }

    public String getGu() {
        return gu;
    }

    public String getDong() {
        return dong;
    }

    public int getPrice() {
        return price;
    }

    public Drawable getProduct() {
        return product;
    }
}
