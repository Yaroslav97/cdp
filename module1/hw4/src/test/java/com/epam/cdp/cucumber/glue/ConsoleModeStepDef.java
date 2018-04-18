package com.epam.cdp.cucumber.glue;

import com.epam.cdp.calculator.ConsoleMode;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsoleModeStepDef {

    private ConsoleMode consoleMode;
    private String actualResult;

    @Given("^init console mode$")
    public void initConsoleMode() throws Throwable {
        consoleMode = new ConsoleMode();
    }

    @When("^set up expression \"([^\"]*)\"$")
    public void setUpExpression(String expression) throws Throwable {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn(expression).thenReturn("stop");

        actualResult = consoleMode.manualEnter(bufferedReader).get(expression);
    }

    @Then("^check result from console \"([^\"]*)\"$")
    public void checkResultFromConsole(String result) throws Throwable {
        assertEquals(result, actualResult);
    }

}
