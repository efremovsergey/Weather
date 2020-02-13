package com.efremov.weather.main;

import androidx.viewpager.widget.ViewPager;

import com.efremov.weather.R;
import com.efremov.weather.base.utils.activity.BaseActivity;
import com.efremov.weather.databinding.MainActivityBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BaseActivity<MainActivityBinding, PageViewModel> {

    public TabsPagerAdapter tabsPagerAdapter;
    public ViewPager viewPager;

    public boolean isNetworkAvailable;

    @Override
    public PageViewModel onCreate() {
        //TODO: оптимизировать методы
        tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        return new PageViewModel(this);
    }

    @Override
    public int getVariable() {
        return com.efremov.weather.BR.mainViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}