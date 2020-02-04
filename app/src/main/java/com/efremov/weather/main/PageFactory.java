package com.efremov.weather.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

public class PageFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final MainActivity activity;

    private final long id;

    public PageFactory(@NonNull MainActivity activity, long id) {
        this.activity = activity;
        this.id = id;
    }

    @NonNull
    public PageViewModel create(PageViewModel modelClass) {
        return new PageViewModel(activity);
    }
}