package com.efremov.weather.base.model.entities;

public class Info {
    boolean f;
    boolean n;
    boolean nr;
    boolean ns;
    boolean nsr;
    boolean p;
    double lat;
    double lon;
    private class tzinfo {
        String name;
        String abbr;
        int offset;
        boolean dst;
    }
    int def_pressure_mm;
    int def_pressure_pa;
    boolean _h;
    String url;
}
