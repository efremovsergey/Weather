package com.efremov.weather.today;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableDouble;
import androidx.databinding.ObservableField;

import com.efremov.weather.R;
import com.efremov.weather.base.model.api.IWeatherRepo;
import com.efremov.weather.base.model.api.WeatherRepo;
import com.efremov.weather.base.model.app.App;
import com.efremov.weather.base.model.binding.BindingAdapters;
import com.efremov.weather.base.model.entities.Weather;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SingleCardFragmentVM extends FragmentViewModel<SingleCardFragment> {

    private IWeatherRepo weatherRepo;

    public final ObservableBoolean isLoading = new ObservableBoolean();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> latlon = new ObservableField<>();
    public final ObservableField<String> temp = new ObservableField();
    public final ObservableField<String> windSpeed = new ObservableField();
    public final ObservableField<String> windDirection = new ObservableField<>();
    public final ObservableField<String> url = new ObservableField<>();
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

    SingleCardFragmentVM(SingleCardFragment fragment) {
        super(fragment);
        weatherRepo = new WeatherRepo();
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        Location location = App.getInstance().getMyLocation();
        city.set(getCityName(location));
        double lat = location != null ? location.getLatitude() : 55.753960;
        double lon = location != null ? location.getLongitude() : 37.620393;
        latlon.set(lat + ", " + lon);
        //TODO: magic numbers to const
        weatherRepo.getWeather(
                this::onWeatherLoaded,
                1,
                lat,
                lon);
    }

    private void onWeatherLoaded(Weather weather) {
        isLoading.set(true);
        if (weather != null) {
            name.set("Успешно");
            url.set(weather.getFact().getIcon());
            temp.set(String.valueOf(weather.getFact().getTemp()));
            windSpeed.set(String.valueOf(weather.getFact().getWind_speed()));
            windDirection.set(weather.getFact().getWind_dir());
        } else {
            name.set("Ошибка");
        }
    }

    private String getCityName(Location location) {
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
}