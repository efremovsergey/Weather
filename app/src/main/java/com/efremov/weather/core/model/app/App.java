package com.efremov.weather.core.model.app;

import android.app.Application;
import android.location.Location;

import com.efremov.weather.core.model.entities.Fact;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App instance;

    private static SimpleDateFormat FORMAT_BY_YYYY_MM_DD = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    private static SimpleDateFormat FORMAT_BY_HH = new SimpleDateFormat("HH", Locale.getDefault());

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

        Date weatherTime = getWeatherDate();
        if (weatherTime != null) {
            Calendar currentTime = Calendar.getInstance();
            if (todayWeather == null || todayWeather.getHour_ts() == null
                    || !FORMAT_BY_YYYY_MM_DD.format(currentTime.getTime()).equals(FORMAT_BY_YYYY_MM_DD.format(weatherTime))) {
                actualizateDate(currentTime);
            }
        }

        cashed = weatherList != null && todayWeather != null;
    }

    private Date getWeatherDate() {
        if (todayWeather.getHour_ts() != null) {
            Calendar itemDate = Calendar.getInstance();
            itemDate.setTimeZone(TimeZone.getDefault());
            itemDate.setTimeInMillis(todayWeather.getHour_ts() * 1000L);
            return itemDate.getTime();
        }

        if (todayWeather.getDate_ts() != null) {
            Calendar itemDate = Calendar.getInstance();
            itemDate.setTimeZone(TimeZone.getDefault());
            itemDate.setTimeInMillis(todayWeather.getDate_ts() * 1000L);
            return itemDate.getTime();
        }

        return null;
    }

    private void actualizateDate(Calendar currentTime) {
        List<Fact> actualizatedWeatherList = new ArrayList<>();
        for (Fact item : weatherList) {
            Calendar itemDate = Calendar.getInstance();
            itemDate.setTimeInMillis(item.getHour_ts() * 1000L);
            itemDate.setTimeZone(currentTime.getTimeZone());
            if (isDatesAreEqual(FORMAT_BY_YYYY_MM_DD, currentTime, itemDate) &&
                    isDatesAreEqual(FORMAT_BY_HH, currentTime, itemDate)) {
                setTodayWeather(item);
            }
            if (isDatesAreEqual(FORMAT_BY_YYYY_MM_DD, currentTime, itemDate) ||
                    itemDate.getTime().after(currentTime.getTime())) {
                actualizatedWeatherList.add(item);
            }
        }
        setWeatherList(actualizatedWeatherList);
    }

    private boolean isDatesAreEqual(SimpleDateFormat formatter, Calendar first, Calendar second) {
        return formatter.format(first.getTime()).equals(formatter.format(second.getTime()));
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
        if (myLocation != null) {
            this.myLocation = myLocation;
        }
    }

    public boolean isCashed() {
        return cashed;
    }
}
