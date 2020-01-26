package com.efremov.weather.model;

import java.util.List;

public class WeatherItem {

    //TODO: change by
    /*
     * {"dt":1553709600,
     * "main":{"temp":288.02,"temp_min":287.858,"temp_max":288.02,"pressure":1007.942,"sea_level":1007.942,"grnd_level":994.369,"humidity":100,"temp_kf":0.16},
     * "weather":[{"dt":800,"main":"Clear","description":"clear sky","icon":"01n"}],
     * "clouds":{"all":0},
     * "wind":{"speed":10.14,"deg":250.188},
     * "sys":{"pod":"n"},
     * "dt_txt":"2019-03-27 18:00:00"}
     * */


    public WeatherItem(int dt, Main main, List<Weather> weather, Clouds clouds, Wind wind, String dt_txt) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.clouds = clouds;
        this.wind = wind;
        this.dt_txt = dt_txt;
    }

    private int dt;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private String dt_txt;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
