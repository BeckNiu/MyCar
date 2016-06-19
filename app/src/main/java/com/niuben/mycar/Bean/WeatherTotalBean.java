package com.niuben.mycar.Bean;

import java.util.List;

/**
 * Created by niuben on 2016/5/9.
 */
public class WeatherTotalBean {
    private List<String> night;
    private List<String> day;
    public List<String> getNight() {
        return night;
    }

    public void setNight(List<String> night) {
        this.night = night;
    }

    public List<String> getDay() {
        return day;
    }

    public void setDay(List<String> day) {
        this.day = day;
    }
}

