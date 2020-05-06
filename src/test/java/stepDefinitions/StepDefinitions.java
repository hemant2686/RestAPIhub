package stepDefinitions;


import static io.restassured.RestAssured.given;
import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIresources;
import resources.TestDataBuild;
import resources.UtilClass;

import static org.junit.Assert.*;

import java.io.IOException;


public class StepDefinitions extends UtilClass{
	RequestSpecification req;
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	static String PlaceId;
	@Given("^When Google API Payload is provided with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void when_google_api_payload_is_provided_with_something_something_something(String name, String language, String address) throws IOException 
    {
	    	res= given().spec(requestSpecMethod()).body(TestDataBuild.addPlaceMethod(name, language, address));
		System.out.println("message for git");
	}

	@When("Google API's {string} with {string} Method is executed")
	public void google_API_s_with_Method_is_executed(String apiResourceName, String method) {
		APIresources apiResAPI=APIresources.valueOf(apiResourceName);
		System.out.println(apiResAPI.getResource());
		
		
		if(method.equalsIgnoreCase("POST"))
			response= res.when().post(apiResAPI.getResource()).then().spec(responseSpecMethod()).extract().response();
		else if(method.equalsIgnoreCase("GET"))
			response= res.when().get(apiResAPI.getResource()).then().spec(responseSpecMethod()).extract().response();
		else if(method.equalsIgnoreCase("DELETE"))
			response= res.when().delete(apiResAPI.getResource()).then().spec(responseSpecMethod()).extract().response();
		
	}

	@Then("Verify that the execution is success")
	public void verify_that_the_execution_is_success() {
	  
			assertEquals(response.getStatusCode(),200);
	}

	@Then("Status is OK")
	public void status_is_OK() {
	    // Write code here that turns the phrase above into concrete actions	
		Assert.assertEquals(parseResponse(response,"status"),"OK");
	}

	@And("Verify that the {string} service is execute GET method with the {string} which matches {string} successfully")
	public void verify_that_the_service_is_executed_with_the_method_which_matches_successfully(String apiResourceName, String ExpectedPlaceid, String expectedName) throws IOException {
		
		PlaceId=parseResponse(response,"place_id");
		
		res.given().spec(requestSpecMethod()).queryParam("place_id", PlaceId);
		google_API_s_with_Method_is_executed(apiResourceName,"GET");
		
		String ActualName=parseResponse(response,"name");
		Assert.assertEquals(ActualName, expectedName);
	}

	@Given("DeletePlace Payload is provided")
	public void deleteplace_Payload_is_provided() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    res=given().spec(requestSpecMethod()).body(TestDataBuild.DeleteAPIpayload(PlaceId));
	}
}