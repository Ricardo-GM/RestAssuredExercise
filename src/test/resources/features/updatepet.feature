Feature: Test update pet endpoint of Petstore API with restassured and cucumber
  As a authorized user
  I want to update pet information with the PUT endpoint of petstore API
  To verify the valid update pet information endpoint flow

  Scenario Outline: Get info of a singular pet with PUT endpoint of Petstore API succesfully
    Given the authorized user sends a PUT request to update pet information
    When the authorized user sends a body request with <id> as already created idPet
    And "<name>" as a petname
    And "<status>" as pet status
    Then the response status of PutEndpoint should 200
    And the response body should have <id> as id
    And "<name>" as a name
    And "<status>" as status


    Examples:

      |id|name|status|
      |1 |Sam|available|
      |2 |Pera|unavailable|
      |3 |Mandarino|available|