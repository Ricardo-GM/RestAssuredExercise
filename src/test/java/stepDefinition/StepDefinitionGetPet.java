package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class StepDefinitionGetPet {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    RequestSpecification request;
    private Response response;


    @When("the authorized user sends {int} as a petId in a GET request to get pet info")
    public void the_authorized_user_sends_as_a_pet_id_in_a_get_request_to_get_pet_info(Integer int1) {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type",  "application/json");
        response = request.get(String.format("/pet/"+int1));

    }
    @Then("the response status of GetEndpoint should be 200")
    public void the_response_status_of_get_endpoint_should_be() {
        response.then().extract().response();
        Assert.assertEquals(200,response.statusCode() );


    }
    @And("the response body should have {string} as a name")
    public void the_response_body_should_have_as_a_name(String string) {
        Assert.assertEquals(string,response.jsonPath().getString("name"));
    }


}
