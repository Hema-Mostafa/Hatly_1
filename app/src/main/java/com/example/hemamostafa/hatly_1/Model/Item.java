package com.example.hemamostafa.hatly_1.Model;

import java.io.Serializable;

public class Item implements Serializable {

    String item_id;
    String item_link;
    String item_price,item_weight,item_size;
    String item_name,item_category,item_photo;
    String quantity;
    ItemDimensions itemDimensions;



    public Item() {
    }

    public Item( String item_link, String item_price, String item_weight, String item_size, String item_name,
                String item_category, String item_photo, String quantity, ItemDimensions itemDimensions) {

        this.item_link = item_link;
        this.item_price = item_price;
        this.item_weight = item_weight;
        this.item_size = item_size;
        this.item_name = item_name;
        this.item_category = item_category;
        this.item_photo = item_photo;
        this.quantity = quantity;
        this.itemDimensions = itemDimensions;
    }

    public Item(String item_id, String item_name, String quantity) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.quantity = quantity;
    }

    public String getItem_link() {
        return item_link;
    }

    public void setItem_link(String item_link) {
        this.item_link = item_link;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_weight() {
        return item_weight;
    }

    public void setItem_weight(String item_weight) {
        this.item_weight = item_weight;
    }

    public String getItem_size() {
        return item_size;
    }

    public void setItem_size(String item_size) {
        this.item_size = item_size;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public String getItem_photo() {
        return item_photo;
    }

    public void setItem_photo(String item_photo) {
        this.item_photo = item_photo;
    }

    public ItemDimensions getItemDimensions() {
        return itemDimensions;
    }

    public void setItemDimensions(ItemDimensions itemDimensions) {
        this.itemDimensions = itemDimensions;
    }



    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
