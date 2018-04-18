package com.epam.cdp.entity;

public class Order {

    private User user;
    private Product product;

    public Order() {
    }

    public Order(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }

}
