package com.efremov.weather.model;

public class WeatherItem {

    //TODO: change by
    /*
     * {"dt":1553709600,"main":{"temp":288.02,"temp_min":287.858,"temp_max":288.02,"pressure":1007.942,"sea_level":1007.942,"grnd_level":994.369,"humidity":100,"temp_kf":0.16},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":10.14,"deg":250.188},"sys":{"pod":"n"},"dt_txt":"2019-03-27 18:00:00"}
     * */


    public WeatherItem(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    private int id;
    private String name;
    private String surname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
