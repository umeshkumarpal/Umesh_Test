package com.example.umesh_test.WeatherModel;

public class Current {

    private Integer last_updated_epoch;
    private String last_updated;
    private Float temp_c;
    private Float temp_f;
    private Integer is_day;

    public Current(Integer last_updated_epoch, String last_updated, Float temp_c, Float temp_f, Integer is_day) {
        this.last_updated_epoch = last_updated_epoch;
        this.last_updated = last_updated;
        this.temp_c = temp_c;
        this.temp_f = temp_f;
        this.is_day = is_day;
    }

    public Integer getLast_updated_epoch() {
        return last_updated_epoch;
    }

    public void setLast_updated_epoch(Integer last_updated_epoch) {
        this.last_updated_epoch = last_updated_epoch;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public Float getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(Float temp_c) {
        this.temp_c = temp_c;
    }

    public Float getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(Float temp_f) {
        this.temp_f = temp_f;
    }

    public Integer getIs_day() {
        return is_day;
    }

    public void setIs_day(Integer is_day) {
        this.is_day = is_day;
    }
}
