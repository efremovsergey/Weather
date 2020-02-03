package com.efremov.weather.today;

import android.location.Location;

import androidx.databinding.ObservableField;

import com.efremov.weather.base.model.App;
import com.efremov.weather.base.model.api.ServerApi;
import com.efremov.weather.base.model.entities.Weather;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleCardFragmentVM extends FragmentViewModel<SingleCardFragment> {

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> latlon = new ObservableField<>();
    public final ObservableField<String> field = new ObservableField<String>() {
        @Override
        public String get() {
            return super.get();
        }

        @Override
        public void set(String value) {
            super.set(value);
        }
    };

    SingleCardFragmentVM(SingleCardFragment fragment) {
        super(fragment);
        loadWeather();
    }

    private void loadWeather() {
        Location location = getActivity().getIntent().getParcelableExtra("LOCATION");
        ServerApi serverApi = App.getInstance().getRetrofit().create(ServerApi.class);

        Call<Weather> weather = serverApi.getWeather(
                location.getLatitude(), location.getLongitude(), 1, "c1cff6b8-21e5-44b8-8003-753e6f737ef6"
        );

        weather.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NotNull Call<Weather> call, @NotNull Response<Weather> response) {
                if (response.isSuccessful()) {
                    name.set("Успешно");
                    latlon.set(response.body().getInfo().getLat() + " " + response.body().getInfo().getLon());
                    //TODO:
                } else {
                    name.set("Ошибка парсинга");
                    //TODO:
                }
            }

            @Override
            public void onFailure(@NotNull Call<Weather> call, @NotNull Throwable t) {
                name.set("Ошибка запроса");
                // TODO:
            }
        });
    }
}