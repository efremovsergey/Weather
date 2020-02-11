package com.efremov.weather.splash;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;

import androidx.databinding.ObservableField;

import com.efremov.weather.R;
import com.efremov.weather.base.model.app.App;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

public class SplashViewModel extends ActivityViewModel<SplashActivity> {

    SplashViewModel(SplashActivity activity) {
        super(activity);
    }

    public final ObservableField<String> loadingState = new ObservableField<>();

    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(this::loadSavedData, 1000);
    }

    private void loadSavedData() {
        loadingState.set("Загружаем данные");
        boolean result = App.getInstance().loadData();
        if (!result) {
            loadingState.set("Данных нет, либо они повреждены!");
            SystemClock.sleep(500);
        }
        Location location = getCurrentLocale();
        App.getInstance().setMyLocation(location);
        loadingState.set(location == null ?  getActivity().getResources().getString(R.string.cant_read_location) : getActivity().getResources().getString(R.string.read_location));
        getActivity().navigate();
    }

    private Location getCurrentLocale() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
        }
        return lm != null ? lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) : null;
    }
}