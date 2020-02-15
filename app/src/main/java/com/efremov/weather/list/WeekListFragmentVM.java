package com.efremov.weather.list;

import android.os.Build;

import com.efremov.weather.R;
import com.efremov.weather.core.model.binding.RecyclerBindingAdapter;
import com.efremov.weather.core.model.entities.Fact;
import com.efremov.weather.core.model.entities.Forecasts;
import com.efremov.weather.core.model.entities.Weather;
import com.efremov.weather.core.viewmodel.BaseFragmentVM;

import java.util.ArrayList;
import java.util.List;

public class WeekListFragmentVM extends BaseFragmentVM<WeekListFragment> {

    private List<Fact> hours;
    private RecyclerBindingAdapter adapter;

    private static final int LAYOUT_HOLDER = R.layout.recycler_view_item;

    public WeekListFragmentVM(WeekListFragment fragment) {
        super(fragment);
        adapter = new RecyclerBindingAdapter(LAYOUT_HOLDER, this);
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        loadWeather();
    }

    @Override
    protected void onWeatherSuccessLoading(Weather weather) {
        super.onWeatherSuccessLoading(weather);
        hours = getHoursListFromForecast(weather.getForecasts());
        setHoursInAdapter(hours);
    }

    @Override
    protected void onWeatherListCacheLoading(List<Fact> listWeather) {
        super.onWeatherListCacheLoading(listWeather);
        hours = listWeather;
        setHoursInAdapter(listWeather);
    }

    public RecyclerBindingAdapter getAdapter() {
        return adapter;
    }

    public Fact getHoursAt(Integer index) {
        if (hours.get(index) != null) {
            return hours.get(index);
        }
        return null;
    }

    private void setHoursInAdapter(List<Fact> hours) {
        this.adapter.setForecasts(hours);
        this.adapter.notifyDataSetChanged();
    }

    private List<Fact> getHoursListFromForecast(List<Forecasts> forecasts) {
        List<Fact> hours = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            forecasts.forEach(item -> hours.addAll(item.getHours()));
        } else {
            for (Forecasts forecast: forecasts) {
                hours.addAll(forecast.getHours());
            }
        }
        return hours;
    }
}