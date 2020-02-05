package com.efremov.weather.today;

import com.efremov.weather.R;
import com.efremov.weather.base.utils.BaseFragment;
import com.efremov.weather.databinding.FragmentSingleCardBinding;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;

public class SingleCardFragment extends BaseFragment<SingleCardFragmentVM, FragmentSingleCardBinding> {

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
        return com.efremov.weather.BR.singleCardViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_single_card;
    }
}