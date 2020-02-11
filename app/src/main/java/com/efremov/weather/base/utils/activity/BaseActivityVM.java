package com.efremov.weather.base.utils.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;


public class BaseActivityVM<T extends BaseActivity> extends ActivityViewModel<T> {

    public BaseActivityVM(T activity) {
        super(activity);
    }

}