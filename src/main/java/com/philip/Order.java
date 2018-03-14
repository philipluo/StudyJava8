package com.philip;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = {"orderType","orderNo"})
public class Order {

    private int orderNo;
    private int orderType;
    private String zipCode;

    private List<OrderDetail> details;

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

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public void addDetails(OrderDetail detail) {
        if(this.details==null){
            this.details = new ArrayList<>();
        }
        this.details.add(detail);
    }

    @Override
    public String toString() {
        StringBuffer detailsBuffer = new StringBuffer();
        details.stream().forEach(orderDetail -> detailsBuffer.append(orderDetail));
        return "Order{" +
                "orderNo=" + orderNo +
                ", orderType=" + orderType +
                ", zipCode='" + zipCode + '\'' +
                ", details={"+detailsBuffer+"}";
    }
}
