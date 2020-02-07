package com.efremov.weather.today;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.efremov.weather.base.model.api.IWeatherRepo;
import com.efremov.weather.base.model.entities.Weather;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import static com.efremov.weather.BR.isLoading;

public class SingleCardFragmentVM extends FragmentViewModel<SingleCardFragment> {

    private IWeatherRepo weatherRepo;

    @BindingAdapter("isLoading")
    public static int isLoading(View view, boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }

//    public final ObservableBoolean
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
//        isLoading.set(false);
        weatherRepo.getWeather(this::onWeatherLoaded, 1, getActivity().getIntent().getParcelableExtra("LOCATION"));
    }

    private void onWeatherLoaded(Weather weather) {
//        isLoading.set(false);
        if (weather != null) {
            name.set("Успешно");
        } else {
            name.set("Ошибка");
        }
    }
}