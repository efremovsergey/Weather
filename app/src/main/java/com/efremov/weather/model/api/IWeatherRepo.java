package com.efremov.weather.model.api;

import com.efremov.weather.base.model.entities.ResponseAnswer;
import com.efremov.weather.model.WeatherItem;

public interface IWeatherRepo {

    void getWeather(Loader<ResponseAnswer, String> loader);

    interface Loader<T, String> {
        void onLoaded(WeatherItem t, String error);
    }
}
