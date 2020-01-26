package com.efremov.weather.today;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import com.efremov.weather.model.Weather;
import com.efremov.weather.model.api.IWeatherRepo;
import com.efremov.weather.model.api.WeatherRepo;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

import java.io.IOException;

public class TodayWeatherViewModel extends ActivityViewModel<TodayWeatherActivity> {

    private IWeatherRepo weatherRepo;
    public final ObservableField<String> name = new ObservableField<>();

    public TodayWeatherViewModel(TodayWeatherActivity activity) {
        super(activity);
        this.weatherRepo = new WeatherRepo();
    }

    @Override
    public void onResume() {
        super.onResume();
        weatherRepo.getWeather(this::onWeatherLoaded);
    }

    private void onWeatherLoaded(Weather weather) {
        name.set(weather.getName());
    }
}