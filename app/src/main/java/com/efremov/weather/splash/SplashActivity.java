package com.efremov.weather.splash;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import androidx.annotation.NonNull;

import com.efremov.weather.R;
import com.efremov.weather.databinding.ActivitySplashBinding;
import com.efremov.weather.main.MainActivity;
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
        checkPermission();
    }

    public void navigate() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public Context getContext() {
        return SplashActivity.this;
    }

    private void checkPermission() {
        if (PackageManager.PERMISSION_GRANTED == checkSelfPermission(
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            getViewModel().onLocationFind(lm != null ? lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) : null);
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PackageManager.PERMISSION_GRANTED == checkSelfPermission(
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            getViewModel().onLocationFind(lm != null ? lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) : null);
        } else {
            getViewModel().onLocationFind(null);
        }
    }
}
