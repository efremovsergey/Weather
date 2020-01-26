package com.efremov.weather.model.api;

import com.efremov.weather.base.model.Api;
import com.efremov.weather.base.model.RequestCallback;
import com.efremov.weather.model.Weather;

import java.io.IOException;

public class WeatherRepo extends Api implements IWeatherRepo {
    @Override
    public void getWeather(final Loader<Weather> loader) {
        //TODO: add request
        getHttpResponse("http://pro.openweathermap.org/data/2.5/forecast/hourly?lat=35&lon=139/", new RequestCallback() {
            @Override
            public void success(String result) {
                loader.onLoaded(new Weather(0, "", ""));
            }

            @Override
            public void error(String error) {
                loader.onLoaded(new Weather(0, "", ""));
            }
        });
//        try {
//            get("pro.openweathermap.org/data/2.5/forecast/hourly?lat=35&lon=139");
//            loader.onLoaded(new Weather(0, "", ""));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
