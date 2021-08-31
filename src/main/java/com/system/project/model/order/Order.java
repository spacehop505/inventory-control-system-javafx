package com.system.project.model.order;

import java.util.Date;

public class Order {

    private int orderId;
    private Date orderDate;

    public Order(){
    }

    public Order(int orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }


    public Order(int orderId) {
        this.orderId = orderId;
    }

    public Order(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date  orderDate) {
        this.orderDate = orderDate;
    }
}
