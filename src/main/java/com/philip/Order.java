package com.philip;

public class Order {

    private int orderNo;
    private int orderType;
    private String zipCode;

    public Order(int orderNo, int orderType) {
        this.orderNo = orderNo;
        this.orderType = orderType;
    }

    public Order(int orderNo, int orderType, String zipCode) {
        this.orderNo = orderNo;
        this.orderType = orderType;
        this.zipCode = zipCode;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo=" + orderNo +
                ", orderType=" + orderType +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
