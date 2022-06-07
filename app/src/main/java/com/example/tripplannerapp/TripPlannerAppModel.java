package com.example.tripplannerapp;

public class TripPlannerAppModel {

    private int user_id;
    private String user_name;
    private int user_age;
    private String user_email;
    private String user_pass;
    private String user_gender;
    private String user_phone;
    private String user_address;
    private String user_birth;

    //constructors

    public TripPlannerAppModel(int user_id, String user_name, int user_age, String user_email, String user_pass, String user_gender, String user_phone, String user_address, String user_birth) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_age = user_age;
        this.user_email = user_email;
        this.user_pass = user_pass;
        this.user_gender = user_gender;
        this.user_phone = user_phone;
        this.user_address = user_address;
        this.user_birth = user_birth;
    }

    public TripPlannerAppModel(int i, String s, String toString, String string) {
    }

    //toString is necessary for printing the contents of a class object

    @Override
    public String toString() {
        return "TripPlannerAppModel{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_age=" + user_age +
                ", user_email='" + user_email + '\'' +
                ", user_pass='" + user_pass + '\'' +
                ", user_gender='" + user_gender + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_birth='" + user_birth + '\'' +
                '}';
    }


    //getters and setters

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }
}
