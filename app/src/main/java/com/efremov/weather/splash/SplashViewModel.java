package com.efremov.weather.splash;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;
import androidx.databinding.ObservableField;

import com.efremov.weather.R;
import com.efremov.weather.core.model.PermissionRequester;
import com.efremov.weather.core.model.app.App;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

public class SplashViewModel extends ActivityViewModel<SplashActivity> {

    SplashViewModel(SplashActivity activity) {
        super(activity);
    }

    public final ObservableField<String> loadingState = new ObservableField<>();
    private Location myLocation;

    @Override
    public void onStart() {
        super.onStart();
        App.getInstance().loadData();
        permissionCheck();
    }

    private void permissionCheck() {
        PermissionRequester.getInstance().requestPermission(new PermissionRequester.OnResultListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onPermissionGranted() {
                LocationManager lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
                myLocation = lm != null ? lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) : null;
                loadingState.set(getStringByResource(myLocation != null ? R.string.read_location : R.string.cant_read_location));
                getActivity().navigate();
            }

            @Override
            public void onPermissionDenied() {
                loadingState.set(getStringByResource(R.string.cant_read_location));
                getActivity().navigate();
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private String getStringByResource(int id) {
        return getActivity().getResources().getString(id);
    }
}