package com.efremov.weather.base.model;

public interface RequestCallback {
    void success(String result);
    void error(String error);
}
