package com.efremov.weather.list;

import androidx.lifecycle.MutableLiveData;

import com.efremov.weather.R;
import com.efremov.weather.base.model.binding.RecyclerBindingAdapter;
import com.efremov.weather.base.model.entities.Forecasts;
import com.efremov.weather.base.model.entities.Weather;
import com.efremov.weather.base.utils.fragment.BaseFragmentVM;

import java.util.List;

public class WeekListFragmentVM extends BaseFragmentVM<WeekListFragment> {

    private MutableLiveData<List<Forecasts>> forecastsMutable;
    private List<Forecasts> forecasts;
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
        forecasts = weather.getForecasts();
//        forecastsMutable.setValue(forecasts);
        setForecastInAdapter(forecasts);
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