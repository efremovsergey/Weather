package com.efremov.weather.base.model.api;

public interface RequestCallback {
    void success(String result);
    void error(String error);
}
