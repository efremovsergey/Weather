package com.efremov.weather.base.model.entities;

import com.efremov.weather.model.WeatherItem;

public class ResponseAnswer {

    public ResponseAnswer(int id, double message, int cnt, WeatherItem weatherItem, String surname) {
        this.cod = id;
        this.message = message;
        this.cnt = cnt;
        this.weatherItem = weatherItem;
        this.surname = surname;
    }

    private int cod;
    private double message;
    private int cnt;
    private WeatherItem weatherItem;
    private String surname;

    public int getId() {
        return cod;
    }

    public void setId(int cod) {
        this.cod = cod;
    }

    public WeatherItem getName() {
        return weatherItem;
    }

    public void setName(WeatherItem weatherItem) {
        this.weatherItem = weatherItem;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
