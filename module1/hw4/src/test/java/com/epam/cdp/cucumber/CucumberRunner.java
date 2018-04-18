package com.epam.cdp.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:build/cucumber/cucumber-html-report",
                "json:build/cucumber/cucumber.json",
                "pretty:build/cucumber/cucumber-pretty.txt",
                "usage:build/cucumber/cucumber-usage.json",
                "junit:build/cucumber/cucumber-results.xml"},

        features = "src/test/resources/features/",
        glue = "com.epam.cdp.cucumber.glue"
)
public class CucumberRunner {
}
