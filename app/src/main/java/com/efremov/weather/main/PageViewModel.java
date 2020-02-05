package com.efremov.weather.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.efremov.weather.base.model.live_data.LiveDataBus;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

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
        LiveDataBus.subscribe(LiveDataBus.SUBJECT_DATA_LOADED, this.getActivity(), (data) -> {
            //TODO: решить вопрос с обновлением элемента
            getActivity().tabsPagerAdapter.reloadPage(getActivity().viewPager.getCurrentItem());
        });

    }

    private void updateCurrentWeather() {
//        if (App.getInstance().getWeatherList().size() == 0) {
//
//        }
    }
}
