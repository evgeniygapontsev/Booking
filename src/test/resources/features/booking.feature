Feature: Booking search
  Scenario: Rating and hotel name should be correct
    Given user is on a search page
    When user is searching for hotel "Кронон"
    Then "Kronon Park Hotel" exists on the result page
    And "Kronon Park Hotel" rating is "9.6"