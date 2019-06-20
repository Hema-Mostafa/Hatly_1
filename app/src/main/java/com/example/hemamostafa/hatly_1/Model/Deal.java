package com.example.hemamostafa.hatly_1.Model;

public class Deal {
    private String shipper_id;
    String traveller_id;
    String welcomMessage;
    String price;
    String deal_id;
    String source , distination;
    String date;
    String weight;
    String shipmentName;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDistination() {
        return distination;
    }

    public void setDistination(String distination) {
        this.distination = distination;
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

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public String getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(String deal_id) {
        this.deal_id = deal_id;
    }

    public Deal() {
    }

    public Deal( String traveller_id,String shipper_id, String welcomMessage,
                String price, String source, String distination, String date, String weight, String shipmentName) {
        this.shipper_id = shipper_id;
        this.traveller_id = traveller_id;
        this.welcomMessage = welcomMessage;
        this.price = price;
        this.source = source;
        this.distination = distination;
        this.date = date;
        this.weight = weight;
        this.shipmentName = shipmentName;
    }

    public String getShipper_id() {
        return shipper_id;
    }

    public void setShipper_id(String shipper_id) {
        this.shipper_id = shipper_id;
    }

    public String getTraveller_id() {
        return traveller_id;
    }

    public void setTraveller_id(String traveller_id) {
        this.traveller_id = traveller_id;
    }

    public String getWelcomMessage() {
        return welcomMessage;
    }

    public void setWelcomMessage(String welcomMessage) {
        this.welcomMessage = welcomMessage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
