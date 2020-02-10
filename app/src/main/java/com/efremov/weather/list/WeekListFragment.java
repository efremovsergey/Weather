package com.efremov.weather.list;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.efremov.weather.R;
import com.efremov.weather.BR;
import com.efremov.weather.base.model.entities.Forecasts;
import com.efremov.weather.base.utils.BaseFragment;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;
import com.efremov.weather.databinding.FragmentWeekListBinding;

import java.util.List;


public class WeekListFragment extends BaseFragment<WeekListFragmentVM, FragmentWeekListBinding> {

    public WeekListFragment() {
        // Required empty public constructor
    }

    public static WeekListFragment newInstance() {
        return new WeekListFragment();
    }

    @Override
    protected WeekListFragmentVM onCreateViewModel(FragmentWeekListBinding binding) {
        return new WeekListFragmentVM(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setupListUpdate();
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_week_list;
    }

    private void setupListUpdate() {
        getViewModel().getForecasts().observe(this, forecasts -> {
            if (forecasts.size() != 0) {
                getViewModel().setForecastInAdapter(forecasts);
            }
        });
    }
}