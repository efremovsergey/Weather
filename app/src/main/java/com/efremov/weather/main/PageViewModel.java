package com.efremov.weather.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;
import androidx.viewpager.widget.PagerAdapter;

import com.efremov.weather.BR;
import com.efremov.weather.base.model.app.App;
import com.efremov.weather.base.model.enums.WorkingStatus;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

public class PageViewModel extends ActivityViewModel<MainActivity> {

    public PageViewModel(MainActivity activity) {
        super(activity);
    }

    TabsPagerAdapter adapter;

    public final ObservableBoolean isNetworking = new ObservableBoolean();

    @Override
    public void onStart() {
        super.onStart();
        createViewPager();
    }

    @Bindable
    public PagerAdapter getPagerAdapter() {
        return adapter;
    }

    private void createViewPager() {
        adapter = new TabsPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());

        notifyPropertyChanged(com.efremov.weather.BR.pagerAdapter);
    }

    public void onRefreshClicked() {
        isNetworking.set(true);
    }

    WorkingStatus setWorkingStatus() {
        boolean isNetworkAvailable = isNetworkConnected();
        if (!isNetworkAvailable) {
            WorkingStatus workingStatus = App.getInstance().isCashed() ? WorkingStatus.CASHED : WorkingStatus.OFFLINE;
            isNetworking.set(workingStatus != WorkingStatus.OFFLINE);
            return workingStatus;
        }

        isNetworking.set(true);
        return WorkingStatus.ONLINE;
    }

    private boolean isNetworkConnected() {
        final ConnectivityManager cm = (ConnectivityManager)getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();
                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network n = cm.getActiveNetwork();
                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);

                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }
        return false;
    }
}
