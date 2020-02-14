package com.example.ddangnmarket.src.main;

public class Location {
    private String si;
    private String gu;
    private String dong;

    public Location(String si, String gu, String dong) {
        this.si = si;
        this.gu = gu;
        this.dong = dong;
    }

    public String getSi() {
        return si;
    }

    public String getGu() {
        return gu;
    }

    public String getDong() {
        return dong;
    }
}
