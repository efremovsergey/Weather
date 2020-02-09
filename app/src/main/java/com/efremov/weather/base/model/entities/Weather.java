package com.efremov.weather.base.model.entities;

import java.util.List;

public class Weather {

    private int now;
    private String nowDt;
    private Info info;
    private Fact fact;
    private List<Forecasts> forecasts;

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

    public List<Forecasts> getForecasts() {
        return forecasts;
    }
}
