package com.efremov.weather.base.model.entities;

import com.efremov.weather.model.WeatherItem;

public class ResponseAnswer {

    public ResponseAnswer(int cod, String message, String error, int cnt, WeatherItem weatherItem) {
        this.cod = cod;
        this.message = message;
        this.error = error;
        this.cnt = cnt;
        this.weatherItem = weatherItem;
    }

    private int cod;
    private String message;
    private String error;
    private int cnt;
    private WeatherItem weatherItem;

    public int getCode() {
        return cod;
    }

    public WeatherItem getWeatherItem() {
        return weatherItem;
    }

    public String getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public String getError() {
        return error;
    }
}
