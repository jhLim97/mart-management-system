package com.company.Model;

public class OrderHistoryDTO {

    public int historyId;
    public int orderCode;
    public int prCode;
    public int prCount;
    public int prPrice;

    public OrderHistoryDTO() { }

    public OrderHistoryDTO(int historyId, int orderCode, int prCode, int prCount, int prPrice) {
        this.historyId = historyId;
        this.orderCode = orderCode;
        this.prCode = prCode;
        this.prCount = prCount;
        this.prPrice = prPrice;
    }

    public void setHistory_id(int historyId) { this.historyId = historyId; }

    public void setOrder_code(int orderCode) { this.orderCode = orderCode; }

    public void setPr_code(int prCode) { this.prCode = prCode; }

    public void setPr_count(int prCount) { this.prCount = prCount; }

    public void setPr_price(int prPrice) { this.prPrice = prPrice; }

    public int getHistory_id() { return historyId; }

    public int getOrder_code() { return orderCode; }

    public int getPr_code() { return prCode; }

    public int getPr_count() { return prCount; }

    public int getPr_price() { return prPrice; }

}
