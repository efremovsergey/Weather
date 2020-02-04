package com.efremov.weather.base.utils;

import com.efremov.weather.R;
import com.efremov.weather.BR;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;
import com.efremov.weather.databinding.FragmentNoInternetBinding;


public class NoInternetFragment extends BindingFragment<NoInternetFragmentVM, FragmentNoInternetBinding> {

    public NoInternetFragment() {
        // Required empty public constructor
    }

    public static NoInternetFragment newInstance() {
        return new NoInternetFragment();
    }

    @Override
    protected NoInternetFragmentVM onCreateViewModel(FragmentNoInternetBinding binding) {
        return new NoInternetFragmentVM(this);
    }

    @Override
    public int getVariable() {
        return BR.noInternetFragmentVM;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_no_internet;
    }

}