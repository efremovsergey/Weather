package com.efremov.weather.model;

public class Sys {
    public Sys(String pod) {
        this.pod = pod;
    }

    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}
