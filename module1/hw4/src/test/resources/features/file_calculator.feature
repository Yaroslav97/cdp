@FileCalculator
Feature: File Calculator

  Background: Calculator
    Given Set up file
    And init file mode

  Scenario: Calculate expression from file
    When set up expression file "5+43+(23-2)"
    And calculate expression from file
    Then check result "69"

  Scenario: Calculate expression from file
    When set up expression file "5+43+(23-2)"
    And calculate expression from file
    Then check result "69"

  Scenario: Calculate expression from file
    When set up expression file "5+(6*4)"
    And calculate expression from file
    Then check result "29"

  Scenario: Calculate expression from file
    When set up expression file "35-(2*4)"
    And calculate expression from file
    Then check result "27"
