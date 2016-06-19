package com.niuben.mycar.Bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by niuben on 2016/5/12.
 */
public class UserBean  extends DataSupport implements Serializable {
    private int id;
    private String user_name;
    private String user_pass;
    private String car_name;
    private String car_time;
    private String user_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_time() {
        return car_time;
    }

    public void setCar_time(String car_time) {
        this.car_time = car_time;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }
}
