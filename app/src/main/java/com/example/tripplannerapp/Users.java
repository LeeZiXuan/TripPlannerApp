package com.example.tripplannerapp;

import com.google.firebase.database.IgnoreExtraProperties;

public class Users {
    String email, password, username, phoneNum;

    public Users() {

    }

    public Users(String email, String password,String username, String phoneNum) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


}
