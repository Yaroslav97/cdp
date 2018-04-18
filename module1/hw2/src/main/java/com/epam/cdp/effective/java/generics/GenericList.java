package com.epam.cdp.effective.java.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GenericList<T extends Number> {

    private List<Number> list = new ArrayList<>();

    /**
     * Put parameter to list
     *
     * @param number the number
     */
    public void put(T number) {
        list.add(number);
    }

    public List<Number> getSortedList() {
        return Collections.unmodifiableList(list.stream().sorted().collect(Collectors.toList()));
    }

}
