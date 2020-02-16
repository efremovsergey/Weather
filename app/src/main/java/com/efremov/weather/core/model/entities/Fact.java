package com.efremov.weather.core.model.entities;

import java.util.HashMap;
import java.util.Map;

public class Fact {

    private static Map<String, String> windDirMap = new HashMap<String, String>() {{
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
    private Integer hour_ts;

    private String date;
    private Integer date_ts;

    private double temp;
    private String icon;
    private String condition;
    private Double wind_speed;
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

    public Double getWind_speed() {
        return wind_speed;
    }

    public String getWind_dir() {
        return windDirMap.get(wind_dir);
    }

    public String getHour() {
        return hour;
    }

    public Integer getHour_ts() {
        return hour_ts;
    }

    public String getDate() {
        return date;
    }

    public Integer getDate_ts() {
        return date_ts;
    }

    public void setDateParams(String date, int date_ts) {
        this.date = date;
        this.date_ts = date_ts;
    }
}
