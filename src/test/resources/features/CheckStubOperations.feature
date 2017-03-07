Feature: Check operations webservice using stub

  Scenario: User calls hello world service and receives a hello world message
    When a user calls the hello world service
    Then the service returns a hello world message
    And the service responds with statuscode 200