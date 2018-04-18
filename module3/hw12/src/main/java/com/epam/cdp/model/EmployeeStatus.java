package com.epam.cdp.model;

public enum EmployeeStatus {
    ACTIVE("active"), INACTIVE("inactive");

    private String name;

    EmployeeStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
