package com.niuben.mycar.Bean;

import java.io.Serializable;

/**
 * Created by niuben on 2016/5/12.
 */
public class CarBean implements Serializable {
    private String Time;
    private String Address;
    private String Thing;

    public CarBean() {
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    public String getThing() {
        return Thing;
    }

    public void setThing(String thing) {
        Thing = thing;
    }
}
