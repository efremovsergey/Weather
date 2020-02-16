package com.efremov.weather.core.model.entities;

import java.util.List;

public class Weather {

    private int now;
    private String nowDt;
    private Fact fact;
    private List<Forecasts> forecasts;

    public int getNow() {
        return now;
    }

    public String getNowDt() {
        return nowDt;
    }

    public Fact getFact() {
        return fact;
    }

    public List<Forecasts> getForecasts() {
        return forecasts;
    }
}
