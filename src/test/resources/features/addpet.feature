Feature: Test add pet endpoint of Petstore API with restassured and cucumber
  As a authorized user
  I want to add a pet with the POST endpoint of petstore API
  To verify the valid add pet endpoint flow

  Scenario Outline: Add a pet with POST endpoint of Petstore API succesfully
    Given the authorized user sends a POST request to add pet endpoint
    When the authorized user sends a body request with <id> as id of pet
    And "<name>" as a name of pet
    And "<status>" as a status of pet
    Then the response status should be 200
    And the response body should have id of type numeric and value <id>

    Examples:
    |id|name|status|
    |1 |Toffie|available|
    |2 |Garra |available|
    |3 |Panda |unavailable |