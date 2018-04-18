package com.epam.cdp;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.epam.cdp.ComparatorUtils.sortListByLength;
import static com.epam.cdp.ComparatorUtils.sortPairList;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        Integer result = 0;

        for (Integer num : numbers) {
            result += num;
        }
        return result;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        List<Pair<String, Long>> pairList = new ArrayList<>();

        for (String word : words) {
            if (!pairList.contains(new Pair<>(word, (long) Collections.frequency(words, word)))) {
                pairList.add(new Pair<>(word, (long) Collections.frequency(words, word)));
            }
        }
        return sortPairList(pairList).subList(0, limit > pairList.size() ? pairList.size() : (int) limit);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        List<String> listOfDuplicates = new ArrayList<>();

        for (int i = 0; i < words.size() - 1; i++) {
            if (frequency(words, words.get(i)) > 1 && !listOfDuplicates.contains(words.get(i).toUpperCase())) {
                listOfDuplicates.add(words.get(i).toUpperCase());
            }
        }
        return sortListByLength(listOfDuplicates).subList(0, limit > listOfDuplicates.size() ? listOfDuplicates.size() : (int) limit);
    }

}
