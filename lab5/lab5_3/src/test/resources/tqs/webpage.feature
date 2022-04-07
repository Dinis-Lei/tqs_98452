Feature: Book a flight

  Scenario: Choose departure and destination
    When I go to "https://blazedemo.com/index.php"
    And select "San Diego" and "Cairo"
    And click on the button
    Then I should be redirected to "https://blazedemo.com/reserve.php"
  
  Scenario: Choose airline
    When I go to "https://blazedemo.com/reserve.php"
    When select 4 th button
    Then I should be redirected to "https://blazedemo.com/purchase.php"
  
  Scenario: Purchase flight
    When I go to "https://blazedemo.com/purchase.php"
    When fill the form with name "Bob", address "123 Street", city "City", state "State", zip code 1234, card type "Visa", credit card number 123456789, month 12, year 2022, name on card "Bob the Bobbers"
    And click Purchase flight
    Then I should be redireted to "https://blazedemo.com/confirmation.php"



