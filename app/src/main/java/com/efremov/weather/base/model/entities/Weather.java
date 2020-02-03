package com.efremov.weather.base.model.entities;

public class Weather {

    private int now;
    private String nowDt;
    private Info info;

    public int getNow() {
        return now;
    }

    public String getNowDt() {
        return nowDt;
    }

    public Info getInfo() {
        return info;
    }
}
