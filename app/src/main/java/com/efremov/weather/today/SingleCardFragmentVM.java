package com.efremov.weather.today;

import android.location.Location;
import android.os.Handler;

import androidx.databinding.ObservableField;

import com.efremov.weather.base.model.entities.Fact;
import com.efremov.weather.base.model.entities.Weather;
import com.efremov.weather.base.utils.fragment.BaseFragmentVM;

public class SingleCardFragmentVM extends BaseFragmentVM<SingleCardFragment> {

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> latlon = new ObservableField<>();
    public final ObservableField<String> temp = new ObservableField<String>();
    public final ObservableField<String> windSpeed = new ObservableField<String>();
    public final ObservableField<String> windDirection = new ObservableField<>();
    public final ObservableField<String> url = new ObservableField<>();

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
        name.set("Данные актуальны");
        displayWeatherInfo(weather.getFact());
    }

    @Override
    protected void onWeatherCacheLoading(Fact todayWeather) {
        super.onWeatherCacheLoading(todayWeather);
        name.set("Данные устарели");
        displayWeatherInfo(todayWeather);
    }

    private void displayWeatherInfo(Fact weather) {
        isError.set(false);
        url.set(weather.getIcon());
        temp.set(String.valueOf(weather.getTemp()));
        windSpeed.set(String.valueOf(weather.getWind_speed()));
        windDirection.set(weather.getWind_dir());
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