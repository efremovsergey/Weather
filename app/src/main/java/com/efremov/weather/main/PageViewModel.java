package com.efremov.weather.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.efremov.weather.base.model.App;
import com.efremov.weather.base.model.api.ServerApi;
import com.efremov.weather.base.model.entities.Weather;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageViewModel extends ActivityViewModel<MainActivity> {

    public PageViewModel(MainActivity activity) {
        super(activity);
    }

    private MutableLiveData<String> mTitle = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mTitle, input -> "Contact not available in " + input);

    public void setIndex(String index) {
        mTitle.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void updateCurrentWeather() {
//        if (App.getInstance().getWeatherList().size() == 0) {
//
//        }
    }
}
