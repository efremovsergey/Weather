package com.efremov.weather.base.model.entities;

public class Info {
    private boolean f;
    private boolean n;
    private boolean nr;
    private boolean ns;
    private boolean nsr;
    private boolean p;
    private double lat;
    private double lon;
    private int def_pressure_mm;
    private int def_pressure_pa;
    private boolean _h;
    private String url;

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
