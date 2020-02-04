package com.efremov.weather.main;

import androidx.viewpager.widget.ViewPager;

import com.efremov.weather.BR;
import com.efremov.weather.R;
import com.efremov.weather.databinding.MainActivityBinding;
import com.google.android.material.tabs.TabLayout;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;

public class MainActivity extends BindingActivity<MainActivityBinding, PageViewModel> {

    public TabsPagerAdapter tabsPagerAdapter;

    @Override
    public PageViewModel onCreate() {
//        setContentView(R.layout.main_activity);
        tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
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

    //TODO: fix this, not mvvm method
    void onRetryNetworkConnection() {
        tabsPagerAdapter.getItem(tabsPagerAdapter.getCurrentPage());
    }
}