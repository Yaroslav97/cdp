package com.epam.cdp.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SpyTest {

    @Spy
    private Calculator calculator;

    @Test
    public void testCalculator() throws Exception {
        assertEquals("6", calculator.calculate("5 + (3 - 2) + ( 5 - 5)"));
    }

}
