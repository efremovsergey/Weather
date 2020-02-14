package com.efremov.weather.base.model.app;

import android.app.Application;
import android.location.Location;

import com.efremov.weather.base.model.entities.Fact;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App instance;

    private static SimpleDateFormat FORMAT_BY_YYYY_MM_DD = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    private static SimpleDateFormat FORMAT_BY_HH_MM = new SimpleDateFormat("HHmm", Locale.getDefault());

    public static synchronized App getInstance() {
        instance = instance == null ? new App() : instance;
        return instance;
    }

    private Prefs prefs;

    private List<Fact> weatherList;
    private Fact todayWeather;
    private Location myLocation;
    private boolean cashed = false;

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

    public void loadData() {
        weatherList = prefs.loadList(Prefs.APP_PREFERENCES_LIST_WEATHER);
        if (weatherList == null) {
            return;
        }

        todayWeather = prefs.loadFact(Prefs.APP_PREFERENCES_TODAY_WEATHER);

        Date currentTime = Calendar.getInstance().getTime();
        if (todayWeather.getHour_ts() != null) {
            Date weatherTime = new Date(todayWeather.getHour_ts());
            if (todayWeather == null || todayWeather.getHour_ts() == null
                    || !FORMAT_BY_YYYY_MM_DD.format(currentTime).equals(FORMAT_BY_YYYY_MM_DD.format(weatherTime))) {
                actualizateDate(currentTime);
            }
        }

        cashed = weatherList != null && todayWeather != null;
    }

    private void actualizateDate(Date currentTime) {
        List<Fact> actualizatedWeatherList = weatherList;
        for (Fact item : weatherList) {
            Date itemTime = new Date((item.getHour_ts()));
            if (FORMAT_BY_HH_MM.format(currentTime).equals(FORMAT_BY_HH_MM.format(itemTime))) {
                todayWeather = item;
            } else if (currentTime.after(itemTime)) {
                actualizatedWeatherList.remove(item);
            }
        }
        weatherList = actualizatedWeatherList;
    }

    public List<Fact> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Fact> weatherList) {
        prefs.saveList(Prefs.APP_PREFERENCES_LIST_WEATHER, weatherList);
        this.weatherList = weatherList;
    }

    public Fact getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(Fact todayWeather) {
        prefs.saveObject(Prefs.APP_PREFERENCES_TODAY_WEATHER, todayWeather);
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

    public boolean isCashed() {
        return cashed;
    }
}
