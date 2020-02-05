package com.efremov.weather.base.model.api;

import android.location.Location;

import com.efremov.weather.base.model.entities.Weather;

public interface IWeatherRepo {
    void getWeather(Loader<Weather> loader, int limit, Location location);

    interface Loader<T> {
        void onLoaded(T t);
        void onError(String error);
    }
}
