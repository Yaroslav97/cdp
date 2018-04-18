package com.epam.cdp.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionRule implements TestRule {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionRule.class);

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    base.evaluate();
                } catch (Exception e) {
                    LOG.error(description.getDisplayName() + " Incorrect expression");
                }
            }
        };
    }

}
