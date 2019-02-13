package com.reactive.domain;

public class Order {

    private int orderID;

    protected String custName;

    protected String custAddress;

    private double totalPrice;

    private double shipFee;

    public Order() {
        super();
    }

    public Order(int orderID, String custName, String custAddress, double totalPrice, double shipFee) {
        super();
        this.orderID = orderID;
        this.custName = custName;
        this.custAddress = custAddress;
        this.totalPrice = totalPrice;
        this.shipFee = shipFee;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getShipFee() {
        return shipFee;
    }

    public void setShipFee(double shipFee) {
        this.shipFee = shipFee;
    }

}
