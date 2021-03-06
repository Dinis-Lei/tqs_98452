Feature: Check Covid Statistics

  Scenario: Check the Default View of a Country
    Given I'm on "http://localhost:3000/"
    When I write on country input: "Portugal"
    And click on search
    Then I should see a table with data for "Portugal" of the last 3 days 


  Scenario Outline: Check the View of a Country for Data of the Last n-days
    Given I'm on "http://localhost:3000/"
    When I write on country input: "<country>"
    And select <n> days
    And click on search
    Then I should see a table with data for "<country>" of the last <n> days
  Examples:
    | country   | n |
    | Portugal  | 5 |
    | USA       | 2 |
    | Spain     | 6 |