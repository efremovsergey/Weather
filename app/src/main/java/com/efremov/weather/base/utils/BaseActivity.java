package com.efremov.weather.base.utils;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleRegistry;

import com.efremov.weather.BR;
import com.efremov.weather.R;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;

import org.jetbrains.annotations.NotNull;


public abstract class BaseActivity<B extends ViewDataBinding, VM extends ActivityViewModel> extends BindingActivity<B, VM> {

    private LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    @NotNull
    @Override
    public LifecycleRegistry getLifecycle() {
        if (mRegistry == null) {
            mRegistry = new LifecycleRegistry(this);
        }
        return mRegistry;
    }
}