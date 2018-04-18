package com.epam.cdp.frequency;

import com.epam.cdp.Aggregator;
import javafx.util.Pair;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public abstract class JavaAggregatorFrequencyTest {

    @Parameterized.Parameter(0)
    public long limit;

    @Parameterized.Parameter(1)
    public List<String> words;

    @Parameterized.Parameter(2)
    public List<Pair<String, Long>> expected;

    private Aggregator aggregator;

    public JavaAggregatorFrequencyTest(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{2, asList("f", "c", "b", "b", "b", "c"), asList(new Pair<>("b", 3L), new Pair<>("c", 2L))});
        data.add(new Object[]{2, asList("f", "f"), asList(new Pair<>("f", 2L))});
        data.add(new Object[]{2, asList("f"), asList(new Pair<>("f", 1L))});
        data.add(new Object[]{2, asList("performance data", "b", "b", "performance data"), asList(new Pair<>("performance data", 2L), new Pair<>("b", 2L))});
        data.add(new Object[]{2, Collections.emptyList(), Collections.emptyList()});

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.addAll(asList("f", "c", "b", "b", "b", "c"));
        }
        data.add(new Object[]{2, list, asList(new Pair<>("b", 300L), new Pair<>("c", 200L))});

        return data;
    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        List<Pair<String, Long>> actual = aggregator.getMostFrequentWords(words, limit);
        assertEquals(expected, actual);
        long finish = System.currentTimeMillis();

        System.out.println(aggregator.getClass() + "(Frequency) - " + (finish - start) + " Millis");
    }
}