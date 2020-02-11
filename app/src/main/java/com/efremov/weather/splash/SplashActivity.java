package com.efremov.weather.splash;

import android.content.Intent;
import android.location.Location;

import com.efremov.weather.R;
import com.efremov.weather.base.utils.activity.BaseActivity;
import com.efremov.weather.databinding.ActivitySplashBinding;
import com.efremov.weather.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    @Override
    public SplashViewModel onCreate() {
        return new SplashViewModel(this);
    }

    @Override
    public int getVariable() {
        return com.efremov.weather.BR.splashViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void navigate() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
