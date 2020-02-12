package com.efremov.weather.base.model.entities;

import java.util.HashMap;
import java.util.Map;

public class Fact {
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

    private String hour;
    private int hour_ts;

    private String date;
    private int date_ts;

    private double temp;
    private String icon;
    private String condition;
    private double wind_speed;
    private String wind_dir;

    public double getTemp() {
        return temp;
    }

    public String getTempString() {
        return String.valueOf(temp);
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

    public String getWindSpeedString() {
        return wind_speed + " м/c";
    }

    public String getWind_dir() {
        return windDirMap.get(wind_dir);
    }

    public String getHour() {
        return (hour.length() == 1 ? "0" + hour : hour) + ":00";
    }

    public int getHour_ts() {
        return hour_ts;
    }

    public String getDate() {
        return date;
    }

    public int getDate_ts() {
        return date_ts;
    }

    public void setDateParams(String date, int date_ts) {
        this.date = date;
        this.date_ts = date_ts;
    }
}
