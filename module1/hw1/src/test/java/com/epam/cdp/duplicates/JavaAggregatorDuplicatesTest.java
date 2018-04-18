package com.epam.cdp.duplicates;

import com.epam.cdp.Aggregator;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public abstract class JavaAggregatorDuplicatesTest {

    @Parameterized.Parameter(0)
    public long limit;

    @Parameterized.Parameter(1)
    public List<String> words;

    @Parameterized.Parameter(2)
    public List<String> expected;

    private Aggregator aggregator;

    public JavaAggregatorDuplicatesTest(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{3, asList("wwwoooo", "performance data", "BA", "wwwoOoo", "ba"), asList("BA", "WWWOOOO")});
        data.add(new Object[]{3, asList("all", "arguments", "performance data", "map", "wwwwwww", "map", "arguments", "all"), asList("ALL", "MAP", "ARGUMENTS")});
        data.add(new Object[]{2, asList("dog", "word", "intellij", "performance data", "b", "dog", "word", "intellij"), asList("DOG", "WORD")});
        data.add(new Object[]{3, Collections.emptyList(), Collections.emptyList()});
        data.add(new Object[]{3, asList("www"), Collections.emptyList()});

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.addAll(asList("dog", "word", "intellij", "performance data", "dog", "word", "intellij"));
        }
        data.add(new Object[]{2, list, asList("DOG", "WORD")});

        return data;
    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        List<String> actual = aggregator.getDuplicates(words, limit);
        assertEquals(expected, actual);
        long finish = System.currentTimeMillis();

        System.out.println(aggregator.getClass() + "(Duplicates) - " + (finish - start) + " Millis");
    }
}