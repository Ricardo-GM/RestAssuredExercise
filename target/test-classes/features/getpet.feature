Feature: Test get pet endpoint of Petstore API with restassured and cucumber
  As a authorized user
  I want to get pet information with the GET endpoint of petstore API
  To verify the valid get pet info endpoint flow

  Scenario Outline: Update info of a singular pet with GET endpoint of Petstore API succesfully
    When the authorized user sends <id> as a petId in a GET request to get pet info
    Then the response status of GetEndpoint should be 200
    And the response body should have "<name>" as a name


  Examples:

    |id|name|
    |1 |Toffie|
    |2 |Garra |
    |3 |Panda |