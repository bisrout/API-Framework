package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resourses.APIResources;
import resourses.TestDataBuild;
import resourses.Utils;
public class StepDefination extends Utils {
	RequestSpecification reqmethod;
	ResponseSpecification resspec;
	Response res2;
   public static  String place_id;
	//private static  Logger LOGGER = LogManager.getLogger(StepDefination.class.getName());
	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String website, String language) throws IOException {
	    // Write code here that turns the phrase above into concrete actions		
		reqmethod= given().spec(requestSpecification()).body(TestDataBuild.addPlacePayload(name,website,language));
		//LOGGER.info(reqmethod.toString());
		
	    
	}

	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resourse, String method) throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions
		resspec=resposeSpecification();
		APIResources resourceAPI=APIResources.valueOf(resourse);
		if(method.equalsIgnoreCase("POST")) { 		
			   res2 = reqmethod.when().post(resourceAPI.getResource()).then().spec(resspec).extract().response();			
			   place_id = Utils.getJsonPath(res2,"place_id");
			   System.out.println(res2.asString());
				
			   }
		
		
			   else if (method.equalsIgnoreCase("GET")) {
			   res2 = reqmethod.when().get(resourceAPI.getResource()).then().spec(resspec).extract().response();			
			   
			   }
			   else if (method.equalsIgnoreCase("DEL")) {
				   res2 = reqmethod.when().delete(resourceAPI.getResource()).then().spec(resspec).extract().response();			
				   
		}
		
	    
	}

	@Then("The API cal got sucess with status code")
	public void the_API_cal_got_sucess_with_status_code() {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(res2.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String Keyvalue, String expectedvalue) {
		// Write code here that turns the phrase above into concrete actions
				
				assertEquals(Utils.getJsonPath(res2,Keyvalue),expectedvalue);
				
	}
	@Then("verify place_id created  maps  to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resorce) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		reqmethod= given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_call_with_http_request(resorce,"GET");
		String actualname = Utils.getJsonPath(res2, "name");
		assertEquals(actualname,expectedName);
	}
	@Then("verify place deleated for {string} using {string}")
	public void verify_place_deleated_for_using(String place_idval, String source) throws IOException {
		
		reqmethod= given().spec(requestSpecification()).body(TestDataBuild.deletePlace(place_id));
		user_call_with_http_request(source,"DEL");
		assertEquals(Utils.getJsonPath(res2, "status"),"OK");
	    
	}

}
