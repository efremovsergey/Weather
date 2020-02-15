package com.efremov.weather.core.model.binding;

import android.view.View;

import androidx.databinding.BindingConversion;

public final class BindingConversions {
    private BindingConversions() {
        throw new AssertionError();
    }

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }

}
