Feature: Webui demo

  @webui
  Scenario: test webui steps (should pass)
    Given user opens spring main page
    Then user checking elements on the spring.io main page

  @webui
  Scenario: test webui steps (should fail)
    Given user opens spring main page
    When user clicks on 0 feature
    Then user checking elements on the spring.io main page