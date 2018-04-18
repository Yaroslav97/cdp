package com.epam.cdp.calculator;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileMode {

    private static final Logger LOG = LoggerFactory.getLogger(FileMode.class);

    private File file;
    private BufferedReader bufferedReader;

    public FileMode(File file, BufferedReader bufferedReader) {
        this.file = file;
        this.bufferedReader = bufferedReader;
    }

    private String readExpressionFromFile() {
        StringBuilder expression = new StringBuilder();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                expression.append(line);
            }
        } catch (IOException e) {
            LOG.error("Cannot read file");
        }

        return expression.toString();
    }

    private void writeResultToFile(String text) {
        try {
            FileUtils.writeStringToFile(file, text);
        } catch (IOException e) {
            LOG.error("Cannot write file");
        }
    }

    /**
     * Calculate expression from file
     *
     * @return result
     */
    public String calculate() {
        Calculator calculator = new Calculator();

        String expression = readExpressionFromFile();
        String result = calculator.calculate(expression);

        writeResultToFile(expression + "\n" + result);

        return result;
    }

}
