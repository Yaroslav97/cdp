@Calculator
Feature: Calculator

  Background: Calculator
    Given init calculator

  @Calculator @PLUS
  Scenario: Calculate expression with plus operation
    When calculate "6+3"
    Then the result is 9

  @Calculator @MINUS
  Scenario: Calculate expression with subtract operation
    When calculate "6-3"
    Then the result is 3

  @Calculator @MULTIPLY
  Scenario: Calculate expression with multiply operation
    When calculate "6*3"
    Then the result is 18

  @Calculator @DIVIDE
  Scenario: Calculate expression with divide operation
    When calculate "20/2"
    Then the result is 10

  @Calculator @BRACKETS1.1
  Scenario: Calculate expression with divide operation
    When calculate "20/2+(5*2)"
    Then the result is 20

  @Calculator @BRACKETS1.2
  Scenario: Calculate expression with divide operation
    When calculate "20+(5-2)"
    Then the result is 23

  @Calculator @BRACKETS1.2
  Scenario: Calculate expression with divide operation
    When calculate "20*3+(5+3-2)"
    Then the result is 66

  @Calculator @BRACKETS1.3
  Scenario: Calculate expression with divide operation
    When calculate "20/4-(3-2)"
    Then the result is 4
