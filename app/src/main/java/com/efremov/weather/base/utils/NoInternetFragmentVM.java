package com.efremov.weather.base.utils;

import android.view.View;

import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

public class NoInternetFragmentVM extends FragmentViewModel<NoInternetFragment> {

    public NoInternetFragmentVM(NoInternetFragment fragment) {
        super(fragment);
    }

    public void buttonRetryClick(View view) {
//        PageFactory pageFactory = new PageFactory(MainActivity.class, 0);
//        PageViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this, pageFactory).get(PageViewModel.class);
//        ViewModelProvider(this, PageViewModel);
    }
}