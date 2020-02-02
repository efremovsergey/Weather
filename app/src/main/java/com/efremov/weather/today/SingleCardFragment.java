package com.efremov.weather.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.efremov.weather.R;
import com.efremov.weather.databinding.FragmentSingleCardBinding;
import com.efremov.weather.today.SingleCardFragmentVM;
import com.efremov.weather.main.PageViewModel;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;

public class SingleCardFragment extends BindingFragment<SingleCardFragmentVM, FragmentSingleCardBinding> {

    public SingleCardFragment() {
        // Required empty public constructor
    }

    public static SingleCardFragment newInstance() {
        return new SingleCardFragment();
    }

    @Override
    protected SingleCardFragmentVM onCreateViewModel(FragmentSingleCardBinding binding) {
        return new SingleCardFragmentVM(this);
    }

    @Override
    public int getVariable() {
        return com.efremov.weather.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_single_card;
    }
}