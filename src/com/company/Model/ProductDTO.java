package com.company.Model;

import java.util.Date;

public class ProductDTO {
    public int prCode;
    public String prName;
    public int price;
    public String location;
    public Date expDate;
    public int amount;
    public String state;

    public ProductDTO(){

    }

    public ProductDTO(int prCode, String prName, int price, String location, Date expDate, int amount, String state) {
        this.prCode = prCode;
        this.prName = prName;
        this.price = price;
        this.location = location;
        this.expDate = expDate;
        this.amount = amount;
        this.state = state;
    }

    public int getPrCode() {
        return prCode;
    }

    public void setPrCode(int prCode) {
        this.prCode = prCode;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
