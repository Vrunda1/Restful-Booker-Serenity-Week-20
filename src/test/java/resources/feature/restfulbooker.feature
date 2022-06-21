
Feature: Testing different request on the restful-booker application

  Scenario: Check if the restful-booker application can be accessed by users
    When User sends a GET request to list endpoint
    Then User must get back a valid status code 200

  Scenario: Check if the new booking  can be created by users
    When User sends a POST request to list endpoint of restful-booker
    Then User must get back a valid status code 200


  Scenario: Check if the booking can be updated by users
    When User sends a PUT request to list endpoint of restful-booker
    Then User must get back a valid status code 200

  Scenario: Check if the booking can be deleted by users
    When User sends a DELETE request to list endpoint of restful-booker
    Then User must get back a valid status code 201
    And User should verify that restful-booker is deleted with 201









