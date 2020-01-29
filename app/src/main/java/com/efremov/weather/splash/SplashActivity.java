package com.efremov.weather.splash;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;

import com.efremov.weather.R;
import com.efremov.weather.databinding.ActivitySplashBinding;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;

public class SplashActivity extends BindingActivity<ActivitySplashBinding, SplashViewModel> {

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
}
