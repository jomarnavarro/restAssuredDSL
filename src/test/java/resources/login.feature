Feature: Login to Finance application

  Scenario: Login exitoso
    Given a new user was created with password test@1234
    When the new user logs into the application
    Then the main page should display a welcome message
    And the main page must display the user's portfolio
