package com.example.mido.sqlitetutorials;

import java.util.ArrayList;

/**
 * Created by Mina on 5/1/2017.
 */

public class Transaction {

    int id;
    String name,phone,product,date,buy_price,sell_price;
    public Transaction() {
    }

    public Transaction(String name, String phone, String product, String buy_price,String sell_price, String date) {
        this.name = name;
        this.phone = phone;
        this.product = product;
        this.date = date;
        this.buy_price=buy_price;
        this.sell_price=sell_price;
    }

    public Transaction(int id, String name, String phone, String product, String buy_price,String sell_price, String date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.product = product;
        this.date = date;
        this.buy_price=buy_price;
        this.sell_price=sell_price;
    }

    public int getId() {
        return id;
    }

    public String getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(String buy_price) {
        this.buy_price = buy_price;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
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


}
