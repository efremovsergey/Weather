package com.efremov.weather.base.utils.fragment;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleRegistry;

import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment<VM extends FragmentViewModel, B extends ViewDataBinding> extends BindingFragment<VM, B> {
    private LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    public BaseFragment() {
        // Required empty public constructor
    }

    @NotNull
    @Override
    public LifecycleRegistry getLifecycle() {
        if (mRegistry == null) {
            mRegistry = new LifecycleRegistry(this);
        }
        return mRegistry;
    }
}
