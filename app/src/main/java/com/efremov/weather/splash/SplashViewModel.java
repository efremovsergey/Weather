package com.efremov.weather.splash;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;

import com.efremov.weather.R;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

import java.security.Permission;
import java.util.Locale;

public class SplashViewModel extends ActivityViewModel<SplashActivity> {

    private static final int ACCESS_FINE_LOCATION = 100;

    public final ObservableField<String> loadingState = new ObservableField<>();

    public SplashViewModel(SplashActivity activity) {
        super(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, ACCESS_FINE_LOCATION);
    }

    public void checkPermission(String permission, int requestCode) {
        // Checking if permission is not granted
        loadingState.set("Получаем местоположение");
        if (ContextCompat.checkSelfPermission(activity.getContext(), permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            checkPermission(permission, requestCode);
        } else {
            Location location = getActivity().getCurrentLocale();
            loadingState.set(location == null ?  getActivity().getResources().getString(R.string.cant_read_location) : getActivity().getResources().getString(R.string.read_location));
            //TODO: pass locale to app
        }
    }
}