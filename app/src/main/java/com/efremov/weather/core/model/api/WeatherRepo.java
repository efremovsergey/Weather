package com.efremov.weather.core.model.api;

import android.location.Location;

import com.efremov.weather.R;
import com.efremov.weather.core.model.app.App;
import com.efremov.weather.core.model.entities.Weather;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepo implements IWeatherRepo {
    @Override
    public void getWeather(Loader<Weather> loader, int limit, Location location) {
        ServerApi serverApi = App.getInstance().getRetrofit().create(ServerApi.class);

        Call<Weather> weather = serverApi.getWeather(
                location.getLatitude(),
                location.getLongitude(),
                1,
                App.getInstance().getString(R.string.yandex_api_key)
        );

        weather.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NotNull Call<Weather> call, @NotNull Response<Weather> response) {
                if (response.isSuccessful()) {
                    loader.onLoaded(response.body(), null);
                } else {
                    loader.onLoaded(null, response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Weather> call, @NotNull Throwable t) {
                loader.onLoaded(null, t.getLocalizedMessage());
            }
        });
    }
}
