package com.epam.cdp.effective.java.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private Map<Fruit, Integer> fruitMap = new HashMap<>();

    /**
     * Fill market
     */
    public void fillFruitMarket() {
        int price = 1;

        for (Fruit fruit : Fruit.values()) {
            fruitMap.put(fruit, price++);
        }
    }

    public Map<Fruit, Integer> getFruitMap() {
        return Collections.unmodifiableMap(fruitMap);
    }
}
