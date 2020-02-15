package com.efremov.weather.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;
import androidx.viewpager.widget.PagerAdapter;

import com.efremov.weather.core.model.app.App;
import com.efremov.weather.core.model.enums.WorkingStatus;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

public class PageViewModel extends ActivityViewModel<MainActivity> {

    public PageViewModel(MainActivity activity) {
        super(activity);
    }

    private TabsPagerAdapter adapter;

    public final ObservableBoolean isNetworking = new ObservableBoolean();
    private WorkingStatus workingStatus = WorkingStatus.ONLINE;

    @Override
    public void onStart() {
        super.onStart();
        createViewPager();
        startConnectivity();
        setWorkingStatus(false);
    }

    @Bindable
    public PagerAdapter getPagerAdapter() {
        return adapter;
    }

    private void createViewPager() {
        adapter = new TabsPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());

        notifyPropertyChanged(com.efremov.weather.BR.pagerAdapter);
    }

    private void setWorkingStatus(boolean isNetworkAvailable) {
        if (!isNetworkAvailable) {
            workingStatus = App.getInstance().isCashed() ? WorkingStatus.CASHED : WorkingStatus.OFFLINE;
            isNetworking.set(workingStatus != WorkingStatus.OFFLINE);
        } else {
            isNetworking.set(true);
            workingStatus = WorkingStatus.ONLINE;
        }
    }

    WorkingStatus getWorkingStatus() {
        return workingStatus;
    }

    private void startConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkRequest.Builder builder = new NetworkRequest.Builder();

        if (connectivityManager != null) {
            connectivityManager.registerNetworkCallback(
                    builder.build(),
                    new ConnectivityManager.NetworkCallback() {
                        @Override
                        public void onAvailable(Network network) {
                            setWorkingStatus(true);
                        }

                        @Override
                        public void onLost(Network network) {
                            setWorkingStatus(false);
                        }
                    }
            );
        }
    }
}
