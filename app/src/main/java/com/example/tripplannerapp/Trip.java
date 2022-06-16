package com.example.tripplannerapp;

import com.google.firebase.database.DatabaseReference;

public class Trip {
    String destination, startDate, endDate, tripName, description, trip_id;

    public Trip() { }

    public Trip(String destination, String startDate, String endDate, String tripName, String description, String trip_id){
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tripName = tripName;
        this.description = description;
        this.trip_id = trip_id;
    }

    public String getDestination() { return destination; }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrip_id() {return trip_id;}

    public void setTrip_id(String trip_id) {this.trip_id = trip_id;}
}
