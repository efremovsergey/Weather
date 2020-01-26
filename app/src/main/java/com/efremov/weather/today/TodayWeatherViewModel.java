package com.efremov.weather.today;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;

import com.efremov.weather.base.ActivityViewModel;

public class TodayWeatherViewModel extends ActivityViewModel<TodayWeatherActivity> {
    public TodayWeatherViewModel(TodayWeatherActivity activity) {
        super(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}