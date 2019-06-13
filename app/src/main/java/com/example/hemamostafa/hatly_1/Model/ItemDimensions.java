package com.example.hemamostafa.hatly_1.Model;

import java.io.Serializable;

public class ItemDimensions implements Serializable{
    String height;
    String widht;
    String lenght;

    public ItemDimensions() {
    }

    public ItemDimensions(String height, String widht, String lenght) {
        this.height = height;
        this.widht = widht;
        this.lenght = lenght;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidht() {
        return widht;
    }

    public void setWidht(String widht) {
        this.widht = widht;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }
}
