package com.efremov.weather.base.model.entities;

import androidx.databinding.BaseObservable;

import java.util.HashMap;
import java.util.Map;

public class Forecasts {
    static Map<String, String> conditionMap = new HashMap<String, String>() {{
        put("clear", "малооблачно");
        put("partly-cloudy", "облачно с прояснениями");
        put("overcast", "пасмурно");
        put("partly-cloudy-and-light-rain", "небольшой дождь");
        put("partly-cloudy-and-rain", "дождь");
        put("overcast-and-rain", "сильный дождь");
        put("overcast-thunderstorms-with-rain", "сильный дождь, гроза");
        put("cloudy-and-light-rain", "небольшой дождь");
        put("overcast-and-light-rain", "небольшой дождь");
        put("cloudy-and-rain", "дождь");
        put("overcast-and-wet-snow", "дождь со снегом");
        put("partly-cloudy-and-light-snow", "небольшой снег");
        put("partly-cloudy-and-snow", "снег");
        put("overcast-and-snow", "снегопад");
        put("cloudy-and-light-snow", "небольшой снег");
        put("overcast-and-light-snow", "небольшой снег");
        put("cloudy-and-snow", "снег");
    }};

    static Map<String, String> windDirMap = new HashMap<String, String>() {{
        put("nw", "северо-западный");
        put("n", "северный");
        put("ne", "северо-восточный");
        put("e", "восточный");
        put("se", "юго-восточный");
        put("s", "южный");
        put("sw", "юго-западный");
        put("w", "западный");
        put("c", "штиль");
    }};

    private double temp;
    private double feels_like;
    private String icon;
    private String condition;
    private double wind_speed;
    private String wind_dir;

    public double getTemp() {
        return temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public String getIcon() {
        return "https://yastatic.net/weather/i/icons/blueye/color/svg/" + icon + ".svg";
    }

    public String getCondition() {
        return conditionMap.get(condition);
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public String getWind_dir() {
        return windDirMap.get(wind_dir);
    }
}
