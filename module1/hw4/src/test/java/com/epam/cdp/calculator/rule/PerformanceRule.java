package com.epam.cdp.calculator.rule;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.File;

public class PerformanceRule implements TestRule {

    private File file;

    public PerformanceRule(File file) {
        this.file = file;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long start = System.currentTimeMillis();

                base.evaluate();

                String output = FileUtils.readFileToString(file) +
                        String.format("Time taken for %s: %s milli sec%n",
                                description.getDisplayName(),
                                System.currentTimeMillis() - start);

                FileUtils.writeStringToFile(file, output);
            }
        };
    }

}
