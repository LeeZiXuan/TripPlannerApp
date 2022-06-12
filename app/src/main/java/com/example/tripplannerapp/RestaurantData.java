package com.example.tripplannerapp;

public class RestaurantData {
    String R_id;
    String R_startDate;
    String R_endDate;
    String R_address;
    String R_dataID;

    public RestaurantData(String r_id, String r_startDate, String r_endDate, String r_address, String r_dataID) {
        R_id = r_id;
        R_startDate = r_startDate;
        R_endDate = r_endDate;
        R_address = r_address;
        R_dataID = r_dataID;
    }

    public String getR_id() {
        return R_id;
    }

    public void setR_id(String r_id) {
        R_id = r_id;
    }

    public String getR_startDate() {
        return R_startDate;
    }

    public void setR_startDate(String r_startDate) {
        R_startDate = r_startDate;
    }

    public String getR_endDate() {
        return R_endDate;
    }

    public void setR_endDate(String r_endDate) {
        R_endDate = r_endDate;
    }

    public String getR_address() {
        return R_address;
    }

    public void setR_address(String r_address) {
        R_address = r_address;
    }

    public String getR_dataID() {
        return R_dataID;
    }

    public void setR_dataID(String r_dataID) {
        R_dataID = r_dataID;
    }
}
