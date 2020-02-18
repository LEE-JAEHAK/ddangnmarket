package com.example.ddangnmarket.src.main.home;

public class Items {
    private String name, gu, dong, product;
    private int price;

    public Items(String name, String gu, String dong, int price, String product) {
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

    public String getProduct() {
        return product;
    }
}
