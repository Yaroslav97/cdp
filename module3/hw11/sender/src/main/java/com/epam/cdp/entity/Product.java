package com.epam.cdp.entity;

public class Product {

    private Goods name;
    private int size;
    private int orderTotal;

    public Product() {
    }

    public Product(Goods name, int size, int orderTotal) {
        this.name = name;
        this.size = size;
        this.orderTotal = orderTotal;
    }

    public Goods getName() {
        return name;
    }

    public void setName(Goods name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}
