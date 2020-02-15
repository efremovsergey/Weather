package com.efremov.weather.core.model.entities;

import android.os.Build;

import java.util.List;

public class Forecasts {

    private String date;
    private int date_ts;
    private List<Fact> hours;


    public String getDate() {
        return date;
    }

    public int getDate_ts() {
        return date_ts;
    }

    public List<Fact> getHours() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            hours.forEach(item -> item.setDateParams(date, date_ts));
        } else {
            for (Fact hour: hours) {
                hour.setDateParams(date, date_ts);
            }
        }
        return hours;
    }
}
