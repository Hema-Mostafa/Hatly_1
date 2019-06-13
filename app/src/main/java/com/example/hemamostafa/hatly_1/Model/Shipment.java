package com.example.hemamostafa.hatly_1.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shipment implements Serializable {
    String shipment_id;
    String creator_id;

    String shipmentName,shipment_Note;
    String from , to;
    String beforeDate;
    //String onProcess;
    ArrayList<Item> itemList;
    // this attribute not initialize
    String shipmentPhoto;
    String shipmentWeight;


    public Shipment() {
    }

    public Shipment(String shipmentName, String shipment_Note, String from, String to, String beforeDate) {
        itemList=new ArrayList<>();
        this.shipmentName = shipmentName;
        this.shipment_Note = shipment_Note;
        this.from = from;
        this.to = to;
        this.beforeDate = beforeDate;
    }
    public Shipment(String shipment_id, String creator_id, String shipment_name,
                    String from, String to, String beforeDate) {
        itemList=new ArrayList<>();
        this.shipment_id = shipment_id;
        this.creator_id = creator_id;
        shipmentName = shipment_name;
        this.from = from;
        this.to = to;
        this.beforeDate = beforeDate;

    }
    public String getShipment_Note() {
        return shipment_Note;
    }

    public void setShipment_Note(String shipment_Note) {
        this.shipment_Note = shipment_Note;
    }




    public String getShipmentPhoto() {
        return  shipmentPhoto;
    }

    public void setShipmentPhoto(String shipmentPhoto) {
        this.shipmentPhoto = shipmentPhoto;
    }

    public String getShipmentWeight() {
        return shipmentWeight;
    }

    public void setShipmentWeight(String shipmentWeight) {
        this.shipmentWeight = shipmentWeight;
    }


    public String getShipment_id() {
        return shipment_id;
    }

    public void setShipment_id(String shipment_id) {
        this.shipment_id = shipment_id;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
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

    public String getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(String beforeDate) {
        this.beforeDate = beforeDate;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemOnList(Item newItem) {

        this.itemList.add(newItem);
    }

}
