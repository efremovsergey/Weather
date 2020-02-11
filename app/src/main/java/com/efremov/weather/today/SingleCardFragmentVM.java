package com.efremov.weather.today;

import android.location.Location;
import android.os.Handler;

import androidx.databinding.ObservableField;

import com.efremov.weather.base.model.entities.Weather;
import com.efremov.weather.base.utils.fragment.BaseFragmentVM;

public class SingleCardFragmentVM extends BaseFragmentVM<SingleCardFragment> {

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> latlon = new ObservableField<>();
    public final ObservableField<String> temp = new ObservableField();
    public final ObservableField<String> windSpeed = new ObservableField();
    public final ObservableField<String> windDirection = new ObservableField<>();
    public final ObservableField<String> url = new ObservableField<>();
    public final ObservableField<String> errorText = new ObservableField<>();

    SingleCardFragmentVM(SingleCardFragment fragment) {
        super(fragment);
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        new Handler().post(this::displayLocationInfo);
        loadWeather();
    }

    @Override
    public void onWeatherSuccessLoading(Weather weather) {
        super.onWeatherSuccessLoading(weather);
        isError.set(false);
        name.set("Данные актуальны");
        url.set(weather.getFact().getIcon());
        temp.set(String.valueOf(weather.getFact().getTemp()));
        windSpeed.set(String.valueOf(weather.getFact().getWind_speed()));
        windDirection.set(weather.getFact().getWind_dir());
    }

    private void displayLocationInfo() {
        Location location = getMyLocation();
        city.set(getCityName(getMyLocation()));
        latlon.set(formatDoubleToString(location.getLatitude(), "%.2f")
                + ", "
                + formatDoubleToString(location.getLongitude(), "%.2f"));
    }

    private String formatDoubleToString(double value, String format) {
        return String.format(format, value);
    }
}