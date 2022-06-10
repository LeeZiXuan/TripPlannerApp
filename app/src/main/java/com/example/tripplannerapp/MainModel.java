package com.example.tripplannerapp;

public class MainModel {
    String hotelName;
    String orderDate;
    String rImg;
    String roomType;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    String price;
    String quantity;


    MainModel(){

    }


    public MainModel(String hotelName, String orderDate, String rImg, String roomType, String price, String quantity) {
        this.hotelName = hotelName;
        this.orderDate = orderDate;
        this.rImg = rImg;
        this.quantity =quantity;
        this.price=price;
        this.roomType = roomType;

    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getrImg() {
        return rImg;
    }

    public void setrImg(String rImg) {
        this.rImg = rImg;
    }



    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

}
