package com.efremov.weather.model.api;

import com.efremov.weather.base.model.Api;
import com.efremov.weather.base.model.RequestCallback;
import com.efremov.weather.model.WeatherItem;

public class WeatherRepo extends Api implements IWeatherRepo {
    @Override
    public void getWeather(final Loader<WeatherItem> loader) {
        //TODO: add request
        getHttpResponse("https://samples.openweathermap.org/data/2.5/forecast/hourly?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22", new RequestCallback() {
            @Override
            public void success(String result) {
                loader.onLoaded(new WeatherItem(0, "", ""));
            }

            @Override
            public void error(String error) {
                loader.onLoaded(new WeatherItem(0, "", ""));
            }
        });
//        try {
//            get("pro.openweathermap.org/data/2.5/forecast/hourly?lat=35&lon=139");
//            loader.onLoaded(new WeatherItem(0, "", ""));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
