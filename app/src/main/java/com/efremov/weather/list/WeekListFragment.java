package com.efremov.weather.list;

import com.efremov.weather.R;
import com.efremov.weather.BR;
import com.efremov.weather.base.utils.fragment.BaseFragment;
import com.efremov.weather.databinding.FragmentWeekListBinding;


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
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_week_list;
    }
}