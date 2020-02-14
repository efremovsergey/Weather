package com.efremov.weather.base.model.binding;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ahmadrosid.svgloader.SvgLoader;
import com.efremov.weather.R;
import com.google.android.material.tabs.TabLayout;

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

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @BindingAdapter({ "setUpWithViewpager" })
    public static void setUpWithViewpager(final TabLayout tabLayout, ViewPager viewPager) {
        viewPager.addOnAdapterChangeListener((viewPager1, oldAdapter, newAdapter) -> {
            if (oldAdapter == null && newAdapter == null) {
                return;
            }
            Log.i("TAG", "onAdapterChanged");
            tabLayout.setupWithViewPager(viewPager1);
        });
    }

    @BindingAdapter("app:onClick")
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(v -> runnable.run());
    }
}
