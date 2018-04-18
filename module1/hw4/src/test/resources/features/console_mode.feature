@ConsoleMode
Feature: ConsoleMode

  Background: Calculator
    Given init console mode

  Scenario: Calculate expression from console
    When set up expression "5+43"
    Then check result from console "48"

  Scenario: Calculate expression from console
    When set up expression "5+43-8"
    Then check result from console "40"

  Scenario: Calculate expression from console
    When set up expression "58-(2*3)"
    Then check result from console "52"

  Scenario: Calculate expression from console
    When set up expression "38+(3*3)"
    Then check result from console "47"

  Scenario: Calculate expression from console
    When set up expression "3*(4*6)"
    Then check result from console "72"
