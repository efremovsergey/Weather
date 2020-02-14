package com.efremov.weather.main;

import com.efremov.weather.R;
import com.efremov.weather.base.model.enums.WorkingStatus;
import com.efremov.weather.base.utils.activity.BaseActivity;
import com.efremov.weather.databinding.MainActivityBinding;

public class MainActivity extends BaseActivity<MainActivityBinding, PageViewModel> {

    @Override
    public PageViewModel onCreate() {
        return new PageViewModel(this);
    }

    @Override
    public int getVariable() {
        return com.efremov.weather.BR.mainViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public WorkingStatus getWeatherStatus() {
        return getViewModel().setWorkingStatus();
    }
}