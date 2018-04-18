package com.epam.cdp.cucumber.glue;

import com.epam.cdp.calculator.FileMode;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;

public class FileModeStepDef {

    private File file;
    private FileMode fileMode;

    @Given("^init file mode$")
    public void initFileMode() throws Throwable {
        fileMode = new FileMode(file, new BufferedReader(new FileReader(file)));
    }

    @And("^Set up file$")
    public void setUpFile() throws Throwable {
        file = new File("src/test/resources/file.txt");
    }

    @When("^set up expression file \"([^\"]*)\"$")
    public void setUpExpressionFile(String expression) throws Throwable {
        FileUtils.writeStringToFile(file, expression);
    }

    @And("^calculate expression from file$")
    public void calculateExpressionFromFile() throws Throwable {
        fileMode.calculate();
    }

    @Then("^check result \"([^\"]*)\"$")
    public void checkResult(String result) throws Throwable {
        assertEquals(FileUtils.readLines(file).get(1), result);
    }

}
