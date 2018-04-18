package com.epam.cdp.model.impl;

public class UserAccount {

    private long id;
    private double score;

    public UserAccount() {
    }

    public UserAccount(long id, double score) {
        this.id = id;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }

}
