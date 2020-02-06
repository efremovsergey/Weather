package com.efremov.weather.base.utils;

import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.efremov.weather.base.model.live_data.LiveDataBus;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

public class NoInternetFragmentVM extends FragmentViewModel<NoInternetFragment> {

    public NoInternetFragmentVM(NoInternetFragment fragment) {
        super(fragment);
    }

    public void buttonRetryClick(View view) {
        LiveDataBus.publish(LiveDataBus.SUBJECT_DATA_LOADED, this);
    }
}