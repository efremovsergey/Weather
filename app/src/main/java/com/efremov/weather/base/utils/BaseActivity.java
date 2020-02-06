package com.efremov.weather.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleRegistry;

import com.efremov.weather.BR;
import com.efremov.weather.R;
import com.efremov.weather.base.model.live_data.LiveDataBus;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public abstract class BaseActivity<B extends ViewDataBinding, VM extends ActivityViewModel> extends BindingActivity<B, VM> {

    private LifecycleRegistry mRegistry = new LifecycleRegistry(this);
    NoInternetFragment noInternetFragment;

    @NotNull
    @Override
    public LifecycleRegistry getLifecycle() {
        if (mRegistry == null) {
            mRegistry = new LifecycleRegistry(this);
        }
        return mRegistry;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LiveDataBus.subscribe(LiveDataBus.SUBJECT_DATA_LOADED, this, (data) -> {
            if (noInternetFragment != null) {
                getSupportFragmentManager().beginTransaction().remove(noInternetFragment).commit();
                noInternetFragment = null;
                shouldHideContent();
            }
        });
    }

    public boolean shouldHideContent() {
        if (!isNetworkConnected()) {
            if (noInternetFragment == null) {
                noInternetFragment = new NoInternetFragment();
            }

//            if (getSupportFragmentManager().findFragmentById(android.R.id.content)==null) {
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, noInternetFragment)
                        .commit();
//            }
            return true;
        }
        return false;
    }

    public boolean isNetworkConnected() {
        final ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

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