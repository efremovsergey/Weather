package com.efremov.weather.splash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

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

    public Context getContext() {
        return SplashActivity.this;
    }

    @SuppressLint("MissingPermission")
    Location getCurrentLocale(){
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        return lm != null ? lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) : null;
    }
}
