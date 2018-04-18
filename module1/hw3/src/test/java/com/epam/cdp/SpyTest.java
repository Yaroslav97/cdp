package com.epam.cdp;

import com.epam.cdp.calculator.Calculator;
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
    public void name() throws Exception {
        assertEquals(calculator.calculate("5 + (3 - 2) + ( 5 - 5)"), "6");
    }

}
