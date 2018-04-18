package com.epam.cdp.cucumber.glue;

import com.epam.cdp.calculator.Calculator;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CalculatorStepDef {

    private Calculator calculator;
    private String actualResult;

    @Given("^init calculator$")
    public void initCalculator() throws Throwable {
        calculator = new Calculator();
    }

    @When("^calculate \"([^\"]*)\"$")
    public void calculate(String expression) throws Throwable {
        actualResult = calculator.calculate(expression);
    }

    @Then("^the result is (\\d+)$")
    public boolean theResultIs(int result) throws Throwable {
        return actualResult.equals(result);
    }

}
