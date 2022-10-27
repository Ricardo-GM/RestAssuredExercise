package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class StepDefinitionAddPet {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    RequestSpecification request;
    private Response response;
    String requestBody = "{\n";


    @Given("the authorized user sends a POST request to add pet endpoint")
    public void the_authorized_user_sends_a_post_request_to() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type",  "application/json");
    }
    @When("the authorized user sends a body request with {int} as id of pet")
    public void the_authorized_user_sends_a_body_request_with_as_id_of_pet(Integer int1) {
        requestBody = requestBody  + String.format("  \"id\": %2d,\n", int1);
    }
    @And("{string} as a name of pet")
    public void garra_as_a_name_of_pet(String string) {
        requestBody = requestBody + String.format("  \"name\": \"%s\",\n", string);
    }
    @And("{string} as a status of pet")
    public void available_as_a_status_of_pet(String string) {
        requestBody = requestBody + String.format("  \"status\": \"%s\"\n}", string);
        response = request.body(requestBody).post("/pet");

    }
    @Then("the response status should be 200")
    public void the_response_status_should_be() {
        response.then().extract().response();

        Assert.assertEquals(200,response.statusCode() );
    }
    @And("the response body should have id of type numeric and value {int}")
    public void the_response_body_should_have_of_type_numeric_and_value(Integer int1) {
        Assert.assertEquals(int1.toString(),response.jsonPath().getString("id"));

    }




}
