Feature: Finance app API requests

  Scenario:  GET the /login resource
    Given API URL is http://vamonos-finance.herokuapp.com/login
    When User sends a GET request
    Then The response status code is 200
    And The content type is HTML


  Scenario: POST the /login resource
    Given API URL is http://vamonos-finance.herokuapp.com/login
    And User includes form parameters username with "Pedro" and password with "Pedro"
    When User sends a POST request
    Then The response status code is 200
    And The content type is HTML
    And The "Set-Cookie" header contains the "session" value