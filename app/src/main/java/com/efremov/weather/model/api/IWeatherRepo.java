package com.efremov.weather.model.api;

import com.efremov.weather.model.WeatherItem;

public interface IWeatherRepo {

    void getWeather(Loader<WeatherItem> loader);

    interface Loader<T> {
        void onLoaded(T t);
    }
}
