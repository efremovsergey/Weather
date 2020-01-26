package com.efremov.weather.base;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;

public abstract class ActivityViewModel<A extends AppCompatActivity>
        extends BaseObservable {

    protected A activity;

    public ActivityViewModel(A activity) {
        this.activity = activity;
    }

    public A getActivity() {
        return activity;
    }

    public void onStart() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Override me!
    }

    public void onResume() {
        //Override me!
    }

    public boolean onBackPressed() {
        //Override me!
        return true;
    }
}