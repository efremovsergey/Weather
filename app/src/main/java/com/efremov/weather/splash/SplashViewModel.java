package com.efremov.weather.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.SystemClock;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;

import com.efremov.weather.R;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

import static com.efremov.weather.base.model.App.app;

public class SplashViewModel extends ActivityViewModel<SplashActivity> {

    private static final int ACCESS_FINE_LOCATION = 100;

    public final ObservableField<String> loadingState = new ObservableField<>();
    public final ObservableField<String> field = new ObservableField<String>() {
        @Override
        public String get() {
            return super.get();
        }

        @Override
        public void set(String value) {
            super.set(value);
        }
    };

    SplashViewModel(SplashActivity activity) {
        super(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onBackKeyPress() {
        return super.onBackKeyPress();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadSavedData() {
        loadingState.set("Загружаем данные");
        boolean result = app().loadData();
        if (!result) {
            loadingState.set("Данных нет, либо они повреждены!");
            SystemClock.sleep(200);
        }
    }

    private void checkPermission(String permission, int requestCode) {
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