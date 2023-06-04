Feature: Framework test

  @webui
  Scenario: test webui steps (should pass)
    Given user opens spring main page
    Then user checking elements on the spring.io main page

  @webui
  Scenario: test webui steps (should fail)
    Given user opens spring main page
    When user clicks on 0 feature
    Then user checking elements on the spring.io main page

  @api
  Scenario Outline: test api steps
    Given user getting "GUEST" token
    When user getting all countries to ship
    Then status code was <statusCode>
    Examples:
    # "results" will be shown in Allure report
      | statusCode | result      |
      | 200        | should pass |
      | 400        | should fail |