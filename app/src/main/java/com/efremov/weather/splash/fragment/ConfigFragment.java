package com.efremov.weather.splash.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.efremov.weather.R;
import com.efremov.weather.BR;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;
import com.efremov.weather.databinding.FragmentConfigBinding;


public class ConfigFragment extends BindingFragment<ConfigFragmentVM, FragmentConfigBinding> {

    public ConfigFragment() {
        // Required empty public constructor
    }

    public static ConfigFragment newInstance() {
        return new ConfigFragment();
    }

    private Context context;

    @Override
    protected ConfigFragmentVM onCreateViewModel(FragmentConfigBinding binding) {
        return new ConfigFragmentVM(this);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_config;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    public Context getContext() {
        return context;
    }
}