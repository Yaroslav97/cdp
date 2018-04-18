package com.epam.cdp.model.impl;

import com.epam.cdp.model.Event;

import java.util.Date;

public class EventImpl implements Event {

    private long id;
    private String title;
    private Date date;
    private double price;

    public EventImpl() {
    }

    public EventImpl(long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public EventImpl(long id, String title, Date date, double price) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.price = price;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }

}
