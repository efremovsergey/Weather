package com.efremov.weather.base.model.binding;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.ahmadrosid.svgloader.SvgLoader;
import com.efremov.weather.R;
import com.efremov.weather.base.model.app.App;
import com.efremov.weather.main.MainActivity;

public class BindingAdapters {
    private BindingAdapters() {
        throw new AssertionError();
    }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        if (url != null) {
            SvgLoader.pluck()
                    .with((Activity) view.getContext())
                    .setPlaceHolder(R.drawable.ic_cloud_queue_black_24dp, R.drawable.ic_signal_cellular_connected_no_internet_0_bar_black_24dp)
                    .load(url, view);
        }
    }

//    @BindingAdapter("app:onClick")
//    public static void bindOnClick(View view, final Runnable runnable) {
//        view.setOnClickListener(v -> runnable.run());
//    }
}
