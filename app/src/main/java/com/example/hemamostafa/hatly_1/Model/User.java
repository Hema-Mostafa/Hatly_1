package com.example.hemamostafa.hatly_1.Model;

public class User {

    String user_id;
    String user_name;
    String user_mail;
    String user_password;
    String user_phone;
    String user_photo;
    String user_rate;
    String user_city;

    public User(String name , String password , String mail ){
        this.user_name = name;
        this.user_mail = mail;
        this.user_password = password;

    }
    public User(String user_id, String user_name, String user_mail,
                String user_password, String user_phone, String user_photo, String user_rate, String user_city) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_password = user_password;
        this.user_phone = user_phone;
        this.user_photo = user_photo;
        this.user_rate = user_rate;
        this.user_city = user_city;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_rate() {
        return user_rate;
    }

    public void setUser_rate(String user_rate) {
        this.user_rate = user_rate;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }
}
