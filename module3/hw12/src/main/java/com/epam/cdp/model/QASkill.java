package com.epam.cdp.model;

public enum QASkill {
    MANUAL("manual"), AUTOMATION("automation");

    private String name;

    QASkill(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
