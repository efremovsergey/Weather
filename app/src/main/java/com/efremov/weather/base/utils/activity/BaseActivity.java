package com.efremov.weather.base.utils.activity;

import android.app.AlertDialog;
import android.os.Process;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleRegistry;

import com.efremov.weather.R;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;

import org.jetbrains.annotations.NotNull;


public abstract class BaseActivity<B extends ViewDataBinding, VM extends ActivityViewModel> extends BindingActivity<B, VM> {

    private LifecycleRegistry mRegistry = new LifecycleRegistry(this);

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
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.exit_title);
        builder.setMessage(R.string.exit_message);
        builder.setNegativeButton(R.string.no, null);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            moveTaskToBack(true);
            Process.killProcess(Process.myPid());
            System.exit(0);
        });
        AlertDialog alertDialog = builder
                .create();
        alertDialog.show();
    }
}