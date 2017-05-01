package com.example.mido.sqlitetutorials;

import java.util.ArrayList;

/**
 * Created by Mina on 5/1/2017.
 */

public class Transaction {

    int id;
    String name,phone,product,price,date;

    public Transaction() {
    }

    public Transaction(String name, String phone, String product, String price, String date) {
        this.name = name;
        this.phone = phone;
        this.product = product;
        this.price = price;
        this.date = date;
    }

    public Transaction(int id, String name, String phone, String product, String price, String date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.product = product;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
