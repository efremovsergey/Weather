package com.efremov.weather.splash;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.efremov.weather.R;
import com.efremov.weather.core.model.PermissionRequester;
import com.efremov.weather.core.view.BaseActivity;
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
//        LocalBroadcastManager.getInstance(getContext()).registerReceiver(PermissionRequester.getInstance()., this);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
    }

    public void navigate() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public Context getContext() {
        return SplashActivity.this;
    }
}
