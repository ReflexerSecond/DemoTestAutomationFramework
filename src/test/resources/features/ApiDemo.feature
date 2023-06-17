Feature: Api demo

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