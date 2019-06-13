package com.example.hemamostafa.hatly_1.Model;

public class DummyClassDeals {
    String to;
    String from;
    String transportationType;
    String date;
    String creator_id;
    String shipper_photo;
    String shipper_name;
    String shipper_rate;

    public DummyClassDeals(String to, String from, String transportationType, String date,
                           String creator_id, String shipper_photo, String shipper_name, String shipper_rate) {
        this.to = to;
        this.from = from;
        this.transportationType = transportationType;
        this.date = date;
        this.creator_id = creator_id;
        this.shipper_photo = shipper_photo;
        this.shipper_name = shipper_name;
        this.shipper_rate = shipper_rate;
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

    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getShipper_photo() {
        return shipper_photo;
    }

    public void setShipper_photo(String shipper_photo) {
        this.shipper_photo = shipper_photo;
    }

    public String getShipper_name() {
        return shipper_name;
    }

    public void setShipper_name(String shipper_name) {
        this.shipper_name = shipper_name;
    }

    public String getShipper_rate() {
        return shipper_rate;
    }

    public void setShipper_rate(String shipper_rate) {
        this.shipper_rate = shipper_rate;
    }
}
