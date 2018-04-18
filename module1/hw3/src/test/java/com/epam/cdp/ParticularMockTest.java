package com.epam.cdp;

import com.epam.cdp.calculator.Calculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ParticularMockTest {

    private static final String EXPECTED_RESULT = "6";
    private static final String EXPRESSION = "3+3";

    @Test
    public void calculatorTest() throws Exception {
        Calculator calculator = mock(Calculator.class);

        doReturn(EXPECTED_RESULT).when(calculator).calculate(EXPRESSION);

        assertEquals(calculator.calculate(EXPRESSION), EXPECTED_RESULT);
    }

}
