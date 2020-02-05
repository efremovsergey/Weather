package com.efremov.weather.today;

import androidx.databinding.ObservableField;

import com.efremov.weather.base.model.api.IWeatherRepo;
import com.efremov.weather.base.model.entities.Weather;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

public class SingleCardFragmentVM extends FragmentViewModel<SingleCardFragment> {

    private IWeatherRepo weatherRepo;

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> latlon = new ObservableField<>();
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
        IWeatherRepo.Loader<Weather> loader = new IWeatherRepo.Loader<Weather>() {

            @Override
            public void onLoaded(Weather weather) {
                name.set("Успешно");
            }

            @Override
            public void onError(String error) {
                name.set(error);
            }
        };
        weatherRepo.getWeather(loader, 1, getActivity().getIntent().getParcelableExtra("LOCATION"));
    }
}