Feature: Framework test

  Scenario: test positive
    Given user getting "GUEST" token
    When user opens different sites
    When user getting all countries to ship
    Then status code was 200

  @skip
  Scenario: Cucumber test negative
    When step should pass
    When step should pass
    When step should pass