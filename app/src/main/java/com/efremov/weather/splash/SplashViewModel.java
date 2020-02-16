package com.efremov.weather.splash;

import android.location.Location;

import androidx.databinding.ObservableField;

import com.efremov.weather.R;
import com.efremov.weather.core.model.app.App;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

public class SplashViewModel extends ActivityViewModel<SplashActivity> {

    SplashViewModel(SplashActivity activity) {
        super(activity);
    }

    public final ObservableField<String> loadingState = new ObservableField<>();

    @Override
    public void onStart() {
        super.onStart();
        loadingState.set(getStringByResource(R.string.loading_data));
        App.getInstance().loadData();
    }

    public void onLocationFind(Location location) {
        loadingState.set(getStringByResource(location != null ? R.string.read_location : R.string.cant_read_location));
        App.getInstance().setMyLocation(location);
        getActivity().navigate();
    }

    private String getStringByResource(int id) {
        return getActivity().getResources().getString(id);
    }
}