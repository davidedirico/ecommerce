Feature: OrderApiTest

Scenario: status code verification when negative quantity setted in order creation

  Given I set uri
  And I create body with "negative" data
  When I create order
  Then I check status code to be equal to 400

  Scenario: order correctly created

    Given I set uri
    And I create body with "correct" data
    When I create order
    Then I check status code to be equal to 200
    And I validate response data

    Scenario Outline: Table example
      Given I set uri
      And I create body with "<action>" data
      When I create order
      Then I check status code to be equal to <statusCode>


    Examples:
      |action|statusCode|
      |negative|400     |
    |correct|200     |






