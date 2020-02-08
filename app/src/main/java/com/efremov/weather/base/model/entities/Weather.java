package com.efremov.weather.base.model.entities;

public class Weather {

    private int now;
    private String nowDt;
    private Info info;
    private Fact fact;

    public int getNow() {
        return now;
    }

    public String getNowDt() {
        return nowDt;
    }

    public Info getInfo() {
        return info;
    }

    public Fact getFact() {
        return fact;
    }
}
