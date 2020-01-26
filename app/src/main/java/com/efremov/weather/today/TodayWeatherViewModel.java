package com.efremov.weather.today;

import androidx.databinding.ObservableField;

import com.efremov.weather.base.model.entities.ResponseAnswer;
import com.efremov.weather.model.WeatherItem;
import com.efremov.weather.model.api.IWeatherRepo;
import com.efremov.weather.model.api.WeatherRepo;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

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

    private void onWeatherLoaded(WeatherItem weather, String error) {
        if (weather == null) {
            name.set(error);
            return;
        }
        name.set(weather.getDt_txt());
    }
}