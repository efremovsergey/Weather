package com.efremov.weather.today;

import androidx.databinding.ObservableField;

import com.efremov.weather.base.model.App;
import com.efremov.weather.base.model.api.ServerApi;
import com.efremov.weather.base.model.entities.Weather;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleCardFragmentVM extends FragmentViewModel<SingleCardFragment> {

    public final ObservableField<String> name = new ObservableField<>();

    public SingleCardFragmentVM(SingleCardFragment fragment) {
        super(fragment);
        loadWeather();
    }

    void loadWeather() {
        ServerApi serverApi = App.getInstance().getRetrofit().create(ServerApi.class);

        Call<Weather> weather = serverApi.getWeather(
                23.0, 23.0, "c1cff6b8-21e5-44b8-8003-753e6f737ef6"
        );

        weather.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    name.set(response.body().getNowDt());
                    //TODO:
                } else {
                    name.set("Ошибка парсинга");
                    //TODO:
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                name.set("Ошибка запроса");
                // TODO:
            }
        });
    }
}