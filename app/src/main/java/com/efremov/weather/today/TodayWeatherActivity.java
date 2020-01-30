package com.efremov.weather.today;

import androidx.appcompat.app.ActionBar;

import com.efremov.weather.BR;
import com.efremov.weather.R;
import com.efremov.weather.databinding.ActivityTodayWeatherBinding;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;

public class TodayWeatherActivity extends BindingActivity<ActivityTodayWeatherBinding, TodayWeatherViewModel> {

    @Override
    public TodayWeatherViewModel onCreate() {
        return new TodayWeatherViewModel(this);
    }

    @Override
    public int getVariable() {
        return BR.weatherViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_today_weather;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
    }
}
