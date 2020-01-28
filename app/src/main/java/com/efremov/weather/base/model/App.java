package com.efremov.weather.base.model;

import android.app.Application;

import com.efremov.weather.model.Weather;

import java.util.List;

public class App extends Application {
    private static App instance;
    public static App app() { return instance; }
    private Prefs prefs;

    private List<Weather> weatherList;
    private Weather todayWeather;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        prefs = new Prefs(this.getApplicationContext());
    }

    public boolean loadData() {
        weatherList = prefs.loadList(Prefs.APP_PREFERENCES_LIST_WEATHER);
        todayWeather = (Weather) prefs.loadObject(Prefs.APP_PREFERENCES_TODAY_WEATHER);

        return (weatherList != null && todayWeather != null);
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public Weather getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(Weather todayWeather) {
        this.todayWeather = todayWeather;
    }
}
