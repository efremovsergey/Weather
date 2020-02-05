package com.efremov.weather.base.model.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Prefs {
    private static final String APP_PREFERENCES = "mysettings";

    static final String APP_PREFERENCES_TODAY_WEATHER = "TodayWeather";
    static final String APP_PREFERENCES_LAST_UPDATED = "LastUpdated";
    static final String APP_PREFERENCES_LIST_WEATHER = "ListWeather";

    private SharedPreferences mSettings;
    Gson gson = new Gson();

    Prefs(Context context) {
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public <T> void saveList(String key, List<T> list) {
        String json = gson.toJson(list);
        saveString(key, json);
    }

    <T> List<T> loadList(String key) {
        String json = loadSavedString(key);
        Type type = new TypeToken<List<T>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveObject(String key, Object value) {
        String json = gson.toJson(value);
        saveString(key, json);
    }

    Object loadObject(String key) {
        String json = loadSavedString(key);
        return gson.fromJson(json, Object.class);
    }

    public void saveString(String key, String value) {
        mSettings.edit().putString(key, value).apply();
    }

    public String loadSavedString(String key) {
        return mSettings.getString(key, "");
    }

    public Date loadDate(String key, Date defValue) {
        if (!mSettings.contains(key + "_value") || !mSettings.contains(key + "_zone")) {
            return defValue;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mSettings.getLong(key + "_value", 0));
        calendar.setTimeZone(TimeZone.getTimeZone(mSettings.getString(key + "_zone", TimeZone.getDefault().getID())));
        return calendar.getTime();
    }

    public void saveDate(String key, Date date, TimeZone zone) {
        mSettings.edit().putLong(key + "_value", date.getTime()).apply();
        mSettings.edit().putString(key + "_zone", zone.getID()).apply();
    }
}
