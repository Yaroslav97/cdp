package com.epam.cdp.cashe;

public class Value<T> {

    private T value;
    private long lastUsage;

    public Value(T value, long lastUsage) {
        this.value = value;
        this.lastUsage = lastUsage;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public long getLastUsage() {
        return lastUsage;
    }

    public void setLastUsage(long lastUsage) {
        this.lastUsage = lastUsage;
    }

}
