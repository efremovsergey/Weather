package com.efremov.weather.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BindingActivity<B extends ViewDataBinding, VM extends ActivityViewModel> extends AppCompatActivity {

    private B binding;
    private VM viewModel;

    public B getBinding() {
        return binding;
    }

    public abstract ActivityViewModel onCreate();
    public abstract @IdRes int getVariable();
    public abstract @LayoutRes int getLayoutId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
    }

    public void bind() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        binding.setVariable(getVariable(), viewModel);
        binding.executePendingBindings();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void onBackPressed() {
        if (!viewModel.onBackPressed()) {
            super.onBackPressed();
        }
    }
}