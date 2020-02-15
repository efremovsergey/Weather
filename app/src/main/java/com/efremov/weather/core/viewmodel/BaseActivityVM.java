package com.efremov.weather.core.viewmodel;

import com.efremov.weather.core.view.BaseActivity;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;


public class BaseActivityVM<T extends BaseActivity> extends ActivityViewModel<T> {

    public BaseActivityVM(T activity) {
        super(activity);
    }

}