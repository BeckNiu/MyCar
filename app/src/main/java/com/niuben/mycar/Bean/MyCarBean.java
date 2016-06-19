package com.niuben.mycar.Bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by niuben on 2016/5/12.
 */
public class MyCarBean extends DataSupport implements Serializable {
    private int userId;
    private int Id;
    private String car_type;
    private String time;
    private String address;
    private String ting;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTing() {
        return ting;
    }

    public void setTing(String ting) {
        this.ting = ting;
    }
}
