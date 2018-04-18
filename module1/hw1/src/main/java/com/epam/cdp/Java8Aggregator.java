package com.epam.cdp;

import javafx.util.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        return words.stream().map(word -> new Pair<>(word, (long) Collections.frequency(words, word))).distinct()
                .sorted((o1, o2) -> (int) (o2.getValue() - o1.getValue())).limit(limit).collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        return words.stream().filter(word -> frequency(words, word) > 1).map(String::toUpperCase).distinct()
                .sorted(Comparator.comparingInt(String::length)).limit(limit).collect(Collectors.toList());
    }

}
