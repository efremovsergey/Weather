package com.efremov.weather.base.model.app;

import android.app.Application;
import android.location.Location;

import com.efremov.weather.base.model.entities.Weather;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App instance;

    public static synchronized App getInstance() {
        instance = instance == null ? new App() : instance;
        return instance;
    }
    private Prefs prefs;

    private List<Weather> weatherList;
    private Weather todayWeather;
    private Location myLocation;

    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        prefs = new Prefs(this.getApplicationContext());
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weather.yandex.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
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

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public Location getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
    }
}
