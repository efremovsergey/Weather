package com.efremov.weather.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.efremov.weather.R;
import com.efremov.weather.list.WeekListFragment;
import com.efremov.weather.today.SingleCardFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES =
            new int[] { R.string.tab_text_1, R.string.tab_text_2 };
    private final Context mContext;
    private final FragmentManager fm;

    public TabsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.fm = fm;
        mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SingleCardFragment.newInstance();
            case 1:
                return WeekListFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}
