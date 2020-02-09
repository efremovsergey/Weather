package com.efremov.weather.list;

import android.location.Location;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;

import com.efremov.weather.R;
import com.efremov.weather.base.model.api.IWeatherRepo;
import com.efremov.weather.base.model.api.WeatherRepo;
import com.efremov.weather.base.model.app.App;
import com.efremov.weather.base.model.binding.RecyclerBindingAdapter;
import com.efremov.weather.base.model.entities.Fact;
import com.efremov.weather.base.model.entities.Forecasts;
import com.efremov.weather.base.model.entities.Weather;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class WeekListFragmentVM extends FragmentViewModel<WeekListFragment> {

    private IWeatherRepo weatherRepo;

    private MutableLiveData<List<Forecasts>> forecastsMutable;
    private List<Forecasts> forecasts;
    private RecyclerBindingAdapter adapter;

    private static final int LAYOUT_HOLDER = R.layout.recycler_view_item;

    public WeekListFragmentVM(WeekListFragment fragment) {
        super(fragment);
        weatherRepo = new WeatherRepo();
        adapter = new RecyclerBindingAdapter(LAYOUT_HOLDER, this);
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        Location location = App.getInstance().getMyLocation();
        double lat = location != null ? location.getLatitude() : 55.753960;
        double lon = location != null ? location.getLongitude() : 37.620393;
        //TODO: magic numbers to const
        weatherRepo.getWeather(
                this::onWeatherLoaded,
                7,
                lat,
                lon);
    }

    private void onWeatherLoaded(Weather weather) {
        forecasts = weather.getForecasts();
        forecastsMutable.setValue(forecasts);
    }


    public RecyclerBindingAdapter getAdapter() {
        return adapter;
    }

    public MutableLiveData<List<Forecasts>> getForecasts() {
        if (forecastsMutable == null) {
            forecastsMutable = new MutableLiveData<>();
        }
        return forecastsMutable;
    }

    public Forecasts getForecastAt(Integer index) {
        if (forecasts.get(index) != null) {
            return forecasts.get(index);
        }
        return null;
    }

    public void setForecastInAdapter(List<Forecasts> forecasts) {
        this.adapter.setForecasts(forecasts);
        this.adapter.notifyDataSetChanged();
    }

}