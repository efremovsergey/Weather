package com.efremov.weather.base.model.app;

import android.app.Application;
import android.location.Location;
import android.os.Build;

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

    public static synchronized App getInstance() {
        instance = instance == null ? new App() : instance;
        return instance;
    }

    private Prefs prefs;

    private List<Fact> weatherList;
    private Fact todayWeather;
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
        todayWeather = prefs.loadFact(Prefs.APP_PREFERENCES_TODAY_WEATHER);

        Date currentTime = Calendar.getInstance().getTime();

        if (weatherList == null) {
            return false;
        }

        if (todayWeather == null || todayWeather.getHour_ts() == null) {
            actualizateDate(currentTime);
            return (weatherList != null && todayWeather != null);
        }
        Date weatherTime = new Date((todayWeather.getHour_ts()));
        SimpleDateFormat formatByYearMonthDay = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        if (!formatByYearMonthDay.format(currentTime).equals(formatByYearMonthDay.format(weatherTime))) {
            actualizateDate(currentTime);
        }

        return (weatherList != null && todayWeather != null);
    }

    private void actualizateDate(Date currentTime) {
        for (Fact item : weatherList) {
            checkDate(item, currentTime);
        }
    }

    private void checkDate(Fact item, Date currentTime) {
        SimpleDateFormat formatByHours = new SimpleDateFormat("HHmm", Locale.getDefault());
        Date itemTime = new Date((item.getHour_ts()));
        if (formatByHours.format(currentTime).equals(formatByHours.format(itemTime))) {
            todayWeather = item;
        }
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
}
