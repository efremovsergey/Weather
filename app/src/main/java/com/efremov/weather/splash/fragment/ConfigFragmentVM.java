package com.efremov.weather.splash.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.os.SystemClock;

import androidx.databinding.ObservableField;

import com.efremov.weather.R;
import com.efremov.weather.base.model.PermissionRequester;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import static com.efremov.weather.base.model.App.app;

public class ConfigFragmentVM extends FragmentViewModel<ConfigFragment> {

    public ConfigFragmentVM(ConfigFragment fragment) {
        super(fragment);
    }

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

    @Override
    public void onResume() {
        super.onResume();
        loadSavedData();
    }

    private void loadSavedData() {
        loadingState.set("Загружаем данные");
        boolean result = app().loadData();
        if (!result) {
            loadingState.set("Данных нет, либо они повреждены!");
        }
        SystemClock.sleep(1500);
        getCurrentLocation();
    }

    private void getCurrentLocation() {
        PermissionRequester.getInstance().requestPermission(new PermissionRequester.OnResultListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onPermissionGranted() {
                LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (lm != null) {
                    lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    loadingState.set(getActivity().getResources().getString(R.string.read_location));
                    //TODO: pass to app
                } else {
                    loadingState.set(getActivity().getResources().getString(R.string.cant_read_location));
                }
            }

            @Override
            public void onPermissionDenied() {
                loadingState.set(getActivity().getResources().getString(R.string.cant_read_location));
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);
    }
}