package com.example.hemamostafa.hatly_1.Model;

import java.io.Serializable;

public class Trip implements Serializable {

    String creator_id;
    String date;
    String from;
    String to;
    String transportationType;
    String trip;
    String weight;
   // String state;


    public Trip() {
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public Trip(String to, String from, String data, String weight, String transportationType) {
        this.to = to;
        this.from = from;
        this.date = data;
        this.weight = weight;

        this.transportationType = transportationType;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    /*public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
*/
    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }
}
