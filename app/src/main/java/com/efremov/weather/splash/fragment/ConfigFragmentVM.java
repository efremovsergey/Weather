package com.efremov.weather.splash.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;

import androidx.databinding.ObservableField;

import com.efremov.weather.R;
import com.efremov.weather.base.model.App;
import com.efremov.weather.main.MainActivity;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

public class ConfigFragmentVM extends FragmentViewModel<ConfigFragment> {

    ConfigFragmentVM(ConfigFragment fragment) {
        super(fragment);
    }

    public final ObservableField<String> loadingState = new ObservableField<>();

    @Override
    public void onResume() {
        super.onResume();
        HandlerThread mThread = new HandlerThread("ServiceArguments",
                HandlerThread.MIN_PRIORITY);
        mThread.start();
        Handler mHandler = new Handler(mThread.getLooper());
        mHandler.post(this::loadSavedData);
    }

    private void loadSavedData() {
        loadingState.set("Загружаем данные");
        boolean result = App.getInstance().loadData();
        if (!result) {
            loadingState.set("Данных нет, либо они повреждены!");
            SystemClock.sleep(500);
        }
        Location location = getCurrentLocale();
        loadingState.set(location == null ?  getActivity().getResources().getString(R.string.cant_read_location) : getActivity().getResources().getString(R.string.read_location));
        navigate(location);
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

    private void navigate(Location location) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("LOCATION", location);
        getActivity().startActivity(intent);
    }
}