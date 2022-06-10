package com.example.tripplannerapp;

public class Activity {
    String act_name, act_type, act_address, act_des, act_startDateTime, act_endDateTime, act_id;

    public Activity() {
    }

    public Activity(String act_name, String act_type, String act_address, String act_des, String act_startDateTime, String act_endDateTime, String act_id) {
        this.act_name = act_name;
        this.act_type = act_type;
        this.act_address = act_address;
        this.act_des = act_des;
        this.act_startDateTime = act_startDateTime;
        this.act_endDateTime = act_endDateTime;
        this.act_id = act_id;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getAct_type() {
        return act_type;
    }

    public void setAct_type(String act_type) {
        this.act_type = act_type;
    }

    public String getAct_address() {
        return act_address;
    }

    public void setAct_address(String act_address) {
        this.act_address = act_address;
    }

    public String getAct_des() {
        return act_des;
    }

    public void setAct_des(String act_des) {
        this.act_des = act_des;
    }

    public String getAct_startDateTime() {
        return act_startDateTime;
    }

    public void setAct_startDateTime(String act_startDateTime) {
        this.act_startDateTime = act_startDateTime;
    }

    public String getAct_endDateTime() {
        return act_endDateTime;
    }

    public void setAct_endDateTime(String act_endDateTime) {
        this.act_endDateTime = act_endDateTime;
    }

    public String getAct_id() {
        return act_id;
    }

    public void setAct_id(String act_id) {
        this.act_id = act_id;
    }
}
