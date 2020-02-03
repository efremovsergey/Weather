package com.efremov.weather.base.model.api;

import com.efremov.weather.base.model.entities.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ServerApi {
    @GET("v1/forecast")
    Call<Weather> getWeather(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("limit") int limit,
            @Header("X-Yandex-API-Key") String yandexKey
    );
}
