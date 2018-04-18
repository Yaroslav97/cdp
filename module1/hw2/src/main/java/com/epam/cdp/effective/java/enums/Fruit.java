package com.epam.cdp.effective.java.enums;

public enum Fruit {

    APPLE("apple"),
    BANANA("banana"),
    PINEAPPLE("pineapple"),
    WATERMELON("watermelon"),
    LEMON("lemon"),
    ORANGE("orange");

    private final String fruit;

    Fruit(String fruit) {
        this.fruit = fruit;
    }

}
