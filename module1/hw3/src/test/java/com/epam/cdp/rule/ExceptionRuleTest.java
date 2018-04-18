package com.epam.cdp.rule;

import com.epam.cdp.calculator.Calculator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExceptionRuleTest {

    @Rule
    public ExceptionRule exceptionRule = new ExceptionRule();

    private Calculator calculator = new Calculator();

    @Parameterized.Parameter()
    public String expression;

    @Parameterized.Parameter(value = 1)
    public String expectedResult;

    @Parameterized.Parameters(name = "{index}: testCalculate({0} = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"50 - (5 * 3) - 5", "30"},
                {"530/((30/3)", "5"}
        });
    }

    @Test
    public void calculatorTest() throws Exception {
        assertThat(calculator.calculate(expression), is(expectedResult));
    }

}
