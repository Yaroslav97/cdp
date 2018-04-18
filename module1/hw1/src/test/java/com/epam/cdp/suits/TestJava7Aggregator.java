package com.epam.cdp.suits;

import com.epam.cdp.duplicates.impl.Java7AggregatorDuplicatesTest;
import com.epam.cdp.frequency.impl.Java7AggregatorFrequencyTest;
import com.epam.cdp.sum.impl.Java7AggregatorSumTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Java7AggregatorFrequencyTest.class,
        Java7AggregatorSumTest.class,
        Java7AggregatorDuplicatesTest.class,
})
public class TestJava7Aggregator {
}
