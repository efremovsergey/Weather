package com.efremov.weather.base.model.api;

import android.location.Location;

import com.efremov.weather.base.model.app.App;
import com.efremov.weather.base.model.entities.Weather;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepo implements IWeatherRepo {
    @Override
    public void getWeather(Loader<Weather> loader, int limit, double lat, double lon) {
        ServerApi serverApi = App.getInstance().getRetrofit().create(ServerApi.class);

        //TODO: api key to strings
        Call<Weather> weather = serverApi.getWeather(
                lat, lon, 1, "c1cff6b8-21e5-44b8-8003-753e6f737ef6"
        );

        weather.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NotNull Call<Weather> call, @NotNull Response<Weather> response) {
                if (response.isSuccessful()) {
                    loader.onLoaded(response.body());
                } else {
                    loader.onLoaded(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Weather> call, @NotNull Throwable t) {
                loader.onLoaded(null);
            }
        });
    }
}
