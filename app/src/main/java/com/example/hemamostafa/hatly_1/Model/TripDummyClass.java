package com.example.hemamostafa.hatly_1.Model;

import java.io.Serializable;

public class TripDummyClass implements Serializable {
    String creator_id;
    String date;
    String from;
    String to;
    String transportationType;
    String trip;
    String weight;
    String traveller_photo;
    String traveller_name;
    String traveller_rate;
    String shipmentName;

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public TripDummyClass(String creator_id, String date, String from, String to, String transportationType, String trip, String weight,
                          String traveller_photo, String traveller_name, String traveller_rate,String shipmentName) {
        this.creator_id = creator_id;
        this.date = date;
        this.from = from;
        this.to = to;
        this.transportationType = transportationType;
        this.trip = trip;
        this.weight = weight;
        this.traveller_photo = traveller_photo;
        this.traveller_name = traveller_name;
        this.traveller_rate = traveller_rate;
        this.shipmentName=shipmentName;

    }


    public TripDummyClass() {

    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTraveller_photo() {
        return traveller_photo;
    }

    public void setTraveller_photo(String traveller_photo) {
        this.traveller_photo = traveller_photo;
    }

    public String getTraveller_name() {
        return traveller_name;
    }

    public void setTraveller_name(String traveller_name) {
        this.traveller_name = traveller_name;
    }

    public String getTraveller_rate() {
        return traveller_rate;
    }

    public void setTraveller_rate(String traveller_rate) {
        this.traveller_rate = traveller_rate;
    }
}
