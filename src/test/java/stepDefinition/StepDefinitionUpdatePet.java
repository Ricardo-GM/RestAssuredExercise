package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class StepDefinitionUpdatePet {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    RequestSpecification request;
    private Response response;
    String requestBody = "{\n";

    @Given("the authorized user sends a PUT request to update pet information")
    public void the_authorized_user_sends_a_put_request_to_update_pet_information() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type",  "application/json");
    }
    @When("the authorized user sends a body request with {int} as already created idPet")
    public void the_authorized_user_sends_a_body_request_with_as_already_created_id_pet(Integer int1) {
        requestBody = requestBody  + String.format("  \"id\": %2d,\n", int1);
        System.out.println(int1);
    }
    @And("{string} as a petname")
    public void as_a_petname(String string) {
        requestBody = requestBody + String.format("  \"name\": \"%s\",\n", string);
    }
    @And("{string} as pet status")
    public void as_pet_status(String string) {
        requestBody = requestBody + String.format("  \"status\": \"%s\"\n}", string);
        response = request.body(requestBody).put("/pet");

    }
    @Then("the response status of PutEndpoint should 200")
    public void the_response_status_of_put_endpoint_should() {
        response.then().extract().response();

        Assert.assertEquals(200,response.statusCode() );
    }
    @And("the response body should have {int} as id")
    public void the_response_body_should_have_as_id(Integer int1) {
        Assert.assertEquals(int1.toString(),response.jsonPath().getString("id"));
    }
    @And("{string} as a name")
    public void as_a_name(String string) {
        Assert.assertEquals(string,response.jsonPath().getString("name"));
    }
    @And("{string} as status")
    public void as_status(String string) {
        Assert.assertEquals(string,response.jsonPath().getString("status"));
    }


}
