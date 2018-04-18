package com.epam.cdp;

import com.epam.cdp.calculator.Calculator;
import com.epam.cdp.rule.PerformanceRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class CalculatorParameterizedTest {

    private Calculator calculator = new Calculator();

    @Rule
    public PerformanceRule customRuleTest =
            new PerformanceRule(new File("test_result.txt"));

    @Parameterized.Parameter()
    public String expression;

    @Parameterized.Parameter(value = 1)
    public String expectedResult;

    @Parameterized.Parameters(name = "{index}: testCalculate({0} = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"5+(5*3)", "20"},
                {"5+(20/5)", "9"},
                {"5*(3+3)", "30"},
                {"(20-5)-10", "5"},
                {"50-(5*3)", "35"},
                {"50/(30/3)", "5"},
        });
    }

    @Test
    public void calculatorTest() throws Exception {
        assertThat(calculator.calculate(expression), is(expectedResult));
    }

}
