Feature: Framework test

  Scenario: Cucumber test positive
    When step should pass
    When user opens different sites
    When step should pass

  Scenario: API test positive
    When step should pass
    When user send get request
    When step should pass


  Scenario: Cucumber test negative
    When step should pass
    When step should pass
    When step should pass