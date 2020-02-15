package com.efremov.weather.core.model.api;

import android.location.Location;

import com.efremov.weather.core.model.entities.Weather;

public interface IWeatherRepo {
    void getWeather(Loader<Weather> loader, int limit, Location location);

    interface Loader<T> {
        void onLoaded(T t, String error);
    }
}
