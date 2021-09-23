package com.example.umesh_test.WeatherModel;

public class Weather {
    private String key;
    private String q;
    private Locations location ;
    private Current  current;

    public Weather(String key,String q,Locations location, Current current) {
        this.key = key;
        this.q =q;
        this.location = location;
        this.current = current;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}

