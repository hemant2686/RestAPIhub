package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
@Before("@DeletePlaceAPI")
public void BeforeDeletePlace() throws IOException {
	

	StepDefinitions obj=new StepDefinitions();
	
	if(StepDefinitions.PlaceId==null)
	{
	obj.when_google_api_payload_is_provided_with_something_something_something("Seminery Hills", "Gal", "Nagar");
	obj.google_API_s_with_Method_is_executed("AddPlaceAPI", "POST");
	obj.verify_that_the_service_is_executed_with_the_method_which_matches_successfully("GetPlaceAPI", "placeId", "Seminery Hills");
	}
	}
}
