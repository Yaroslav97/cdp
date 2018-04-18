package com.epam.cdp.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleMode {

    private Calculator calculator = new Calculator();
    private Map<String, String> resultList = new LinkedHashMap<>();

    /**
     * Read expression from console
     *
     * @param reader the bufferReader
     * @return map with results
     * @throws IOException the io exception
     */
    public Map<String, String> manualEnter(BufferedReader reader) throws IOException {
        System.out.println("Enter the expression: ");

        String expression = reader.readLine();

        if (expression.equalsIgnoreCase("stop")) {
            return resultList;
        }

        String result = calculator.calculate(expression);
        System.out.println(result);
        resultList.put(expression, result);

        return manualEnter(reader);
    }

}
