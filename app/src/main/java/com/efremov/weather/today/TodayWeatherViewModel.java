package com.efremov.weather.today;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.efremov.weather.BR;
import com.efremov.weather.base.model.entities.ResponseAnswer;
import com.efremov.weather.model.WeatherItem;
import com.efremov.weather.model.api.IWeatherRepo;
import com.efremov.weather.model.api.WeatherRepo;
import com.google.android.material.tabs.TabLayout;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class TodayWeatherViewModel extends ActivityViewModel<TodayWeatherActivity> {

    FragmentActivity mContext;
    ViewPagerAdapter adapter;
    private IWeatherRepo weatherRepo;
    //    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<CustomTab> viewPager = new ObservableField<CustomTab>() {
        @Override
        public CustomTab get() {
            return super.get();
        }

        @Override
        public void set(CustomTab value) {
            super.set(value);
        }
    };

    public TodayWeatherViewModel(TodayWeatherActivity activity, FragmentActivity context) {
        super(activity);
        this.weatherRepo = new WeatherRepo();
        mContext = context;
        new Handler().postDelayed(() -> createViewPager(), 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        weatherRepo.getWeather(this::onWeatherLoaded);
    }

    @Bindable
    public PagerAdapter getPagerAdapter() {
        return adapter;
    }

    private void createViewPager() {
        adapter = new ViewPagerAdapter(mContext.getSupportFragmentManager());
        adapter.addFrag(new Fragment1(), "Tab 1");
        adapter.addFrag(new Fragment1(), "Tab 2");

        notifyPropertyChanged(viewPager.get().getId());
    }

    private void onWeatherLoaded(WeatherItem weather, String error) {
//        if (weather == null) {
//            name.set(error);
//            return;
//        }
//        name.set(weather.getDt_txt());
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}