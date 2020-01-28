package com.efremov.weather.model.api;

import com.efremov.weather.base.model.api.Api;
import com.efremov.weather.base.model.api.RequestCallback;
import com.efremov.weather.base.model.entities.ResponseAnswer;
import com.google.gson.Gson;

public class WeatherRepo extends Api implements IWeatherRepo {
    @Override
    public void getWeather(Loader<ResponseAnswer, String> loader) {
        getHttpResponse("https://samples.openweathermap.org/data/2.5/forecast/hourly?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22", new RequestCallback() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                ResponseAnswer responseAnswer = gson.fromJson(result, ResponseAnswer.class);
                if (responseAnswer.getError() == null) {
                    loader.onLoaded(responseAnswer.getWeatherItem(), "");
                } else {
                    String errorString = responseAnswer.getMessage() == null ? "Ошибка запроса " + responseAnswer.getError() : responseAnswer.getMessage();
                    loader.onLoaded(null,errorString);
                }
            }

            @Override
            public void error(String error) {
                loader.onLoaded(null, error);
            }
        });
    }
}
