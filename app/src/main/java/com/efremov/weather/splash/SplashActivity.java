package com.efremov.weather.splash;

import com.efremov.weather.R;
import com.efremov.weather.databinding.ActivitySplashBinding;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;

public class SplashActivity extends BindingActivity<ActivitySplashBinding, SplashViewModel> {

    @Override
    public SplashViewModel onCreate() {
        return new SplashViewModel(this);
    }

    @Override
    public int getVariable() {
        return com.efremov.weather.BR.splashViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        setContentView(R.layout.fragment_config);
    }
}
