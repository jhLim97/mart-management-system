package com.company.Model;

public class OrderDTO {

    public int orderCode;
    public int entryPrice;
    public String cName;
    public String phoneNum;
    public String buyDate; // 일단 날짜는 string으로 받아둠...

    public OrderDTO() { }

    public OrderDTO(int orderCode, int entryPrice, String cName, String phoneNum, String buyDate) {
        this.orderCode = orderCode;
        this.entryPrice = entryPrice;
        this.cName = cName;
        this.phoneNum = phoneNum;
        this.buyDate = buyDate;
    }

    public void setOrder_code(int orderCode) {
        this.orderCode = orderCode;
    }

    public void setEntry_price(int entryPrice) {
        this.entryPrice = entryPrice;
    }

    public void setC_name(String cName) {
        this.cName = cName;
    }

    public void setPhone_num(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setBuy_date(String buyDate) {
        this.buyDate = buyDate;
    }

    public int getOrder_code() {
        return orderCode;
    }

    public int getEntry_price() {
        return entryPrice;
    }

    public String getC_name() {
        return cName;
    }

    public String getPhone_num() {
        return phoneNum;
    }

    public String getBuy_date() {
        return buyDate;
    }





}
