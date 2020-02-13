package com.efremov.weather.base.utils.fragment;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.efremov.weather.R;
import com.efremov.weather.base.model.api.IWeatherRepo;
import com.efremov.weather.base.model.api.WeatherRepo;
import com.efremov.weather.base.model.app.App;
import com.efremov.weather.base.model.entities.Fact;
import com.efremov.weather.base.model.entities.Forecasts;
import com.efremov.weather.base.model.entities.Weather;
import com.efremov.weather.main.MainActivity;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BaseFragmentVM<T extends BaseFragment> extends FragmentViewModel<T> {

    private IWeatherRepo weatherRepo;
    private Location location;

    public final ObservableBoolean isLoading = new ObservableBoolean();
    public final ObservableBoolean isRefreshing = new ObservableBoolean();
    public final ObservableBoolean isError = new ObservableBoolean();
    public final ObservableField<String> errorDescription = new ObservableField();

    public BaseFragmentVM(T fragment) {
        super(fragment);
        weatherRepo = new WeatherRepo();
        location = App.getInstance().getMyLocation();
        if (location == null) {
            location = new Location("");
            location.setLatitude(55.753960);
            location.setLongitude(37.620393);
        }
    }

    protected void loadWeather() {
        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        if (!mainActivity.isNetworkAvailable) {
            Fact todayWeather = App.getInstance().getTodayWeather();
            List<Fact> listWeather = App.getInstance().getWeatherList();

            if (todayWeather != null && listWeather != null) {
                onWeatherCacheLoading(todayWeather);
                onWeatherListCacheLoading(listWeather);
            } else {
                onWeatherLoaded(null, "Ошибка чтения данных из кэша!");
            }
        } else {
            isLoading.set(true);
            weatherRepo.getWeather(
                    this::onWeatherLoaded,
                    7,
                    location);
        }
    }

    private void onWeatherLoaded(Weather weather, String error) {
        isLoading.set(false);
        isRefreshing.set(false);
        isError.set(error != null);
        if (error != null) {
            errorDescription.set(error);
        } else {
            App.getInstance().setTodayWeather(weather.getFact());
            List<Fact> weatherList = new ArrayList<>();
            for (Forecasts forecast: weather.getForecasts()) {
                weatherList.addAll(forecast.getHours());
            }
            App.getInstance().setWeatherList(weatherList);
            onWeatherSuccessLoading(weather);
        }
    }

    protected void onWeatherSuccessLoading(Weather weather) {}

    protected void onWeatherCacheLoading(Fact todayWeather) {}

    protected void onWeatherListCacheLoading(List<Fact> listWeather) {}

    protected String getCityName(Location location) {
        if (location == null) {
            return getFragment().getString(R.string.default_city);
        }
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> list = null;
        try {
            list = geocoder.getFromLocation(location
                    .getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
            return "Неизвестно";
        }
        if (list != null & list.size() > 0) {
            Address address = list.get(0);
            return address.getLocality();
        }
        return "Неизвестно";
    }

    public Location getMyLocation() {
        return location;
    }

    public void onRefresh() {
        isRefreshing.set(true);
        loadWeather();
    }
}