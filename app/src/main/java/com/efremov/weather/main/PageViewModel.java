package com.efremov.weather.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

public class PageViewModel extends ActivityViewModel<MainActivity> {

    public PageViewModel(MainActivity activity) {
        super(activity);
    }

    public final ObservableBoolean isNetworking = new ObservableBoolean();

    private MutableLiveData<String> mTitle = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mTitle, input -> "Contact not available in " + input);

    public void setIndex(String index) {
        mTitle.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    @Override
    public void onStart() {
        super.onStart();
        boolean isNetworkAvailable = isNetworkConnected();
        getActivity().isNetworkAvailable = isNetworkAvailable;
        isNetworking.set(isNetworkAvailable);
    }

    public void onRefreshClicked() {
        isNetworking.set(true);
    }

    public boolean isNetworkConnected() {
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
