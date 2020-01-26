package com.efremov.weather.model.api;

import com.efremov.weather.model.Weather;

import java.io.IOException;

public interface IWeatherRepo {

    void getWeather(Loader<Weather> loader);

    interface Loader<T> {
        void onLoaded(T t);
    }
}
