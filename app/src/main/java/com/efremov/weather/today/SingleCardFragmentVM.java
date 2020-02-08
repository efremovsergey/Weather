package com.efremov.weather.today;

import android.location.Location;
import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.efremov.weather.base.model.api.IWeatherRepo;
import com.efremov.weather.base.model.api.WeatherRepo;
import com.efremov.weather.base.model.app.App;
import com.efremov.weather.base.model.entities.Weather;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

public class SingleCardFragmentVM extends FragmentViewModel<SingleCardFragment> {

    private IWeatherRepo weatherRepo;

    public final ObservableBoolean isLoading = new ObservableBoolean();
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
        weatherRepo = new WeatherRepo();
    }

    @Override
    public void onStart() {
        super.onStart();
        Location location = App.getInstance().getMyLocation();
        //TODO: magic numbers to const
        weatherRepo.getWeather(
                this::onWeatherLoaded,
                1,
                location != null ? location.getLatitude() : 55.753960,
                location != null ? location.getLongitude() : 37.620393);
    }

    private void onWeatherLoaded(Weather weather) {
        isLoading.set(true);
        if (weather != null) {
            name.set("Успешно");
        } else {
            name.set("Ошибка");
        }
    }
}