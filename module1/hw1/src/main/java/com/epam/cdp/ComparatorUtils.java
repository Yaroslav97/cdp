package com.epam.cdp;

import javafx.util.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorUtils {

    /**
     * Sort list of pairs by value
     * @param pairList list
     * @return list
     */
    public static List<Pair<String, Long>> sortPairList(List<Pair<String, Long>> pairList) {
        Collections.sort(pairList, new Comparator<Pair<String, Long>>() {
            @Override
            public int compare(Pair<String, Long> o1, Pair<String, Long> o2) {
                if (!o1.equals(o2)) {
                    return (int) (o2.getValue() - o1.getValue());
                } else {
                    return o2.getKey().compareTo(o1.getKey());
                }
            }
        });
        return pairList;
    }

    /**
     * Sort list of strings by length
     * @param list list
     * @return list
     */
    public static List<String> sortListByLength(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                } else {
                    return o1.compareTo(o2);
                }
            }

        });
        return list;
    }

}
