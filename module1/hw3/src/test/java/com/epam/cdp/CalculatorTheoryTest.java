package com.epam.cdp;

import com.epam.cdp.calculator.Calculator;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeThat;

@RunWith(Theories.class)
public class CalculatorTheoryTest {

    private static Calculator calculator = new Calculator();

    @DataPoint
    public static String EXPRESSION = calculator.calculate("5+(3+2)");


    @DataPoints("expression")
    public static List<String> expression() {
        return Arrays.asList(
                "5-(10/2)+6",
                "34+6",
                "4*(5-1)"
        );
    }

    @DataPoints("result")
    public static List<String> result() {
        return Arrays.asList(
                "6",
                "40",
                "16"
        );
    }

    @Theory
    public void calculatorTest(String expression) throws Exception {
        assumeThat(expression, is("10"));
    }

    @Theory
    public void calculatorTest(@FromDataPoints("expression") String expression,
                                       @FromDataPoints("result") String result) {
        assumeThat(calculator.calculate(expression), is(result));
    }
}
