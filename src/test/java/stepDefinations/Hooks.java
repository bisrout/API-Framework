package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
    
	
		StepDefination m = new StepDefination();
		if(StepDefination.place_id ==null) {
			 
			m.add_Place_Payload("jenkin", "www.normal.com", "Spanish");
			m.user_call_with_http_request("AddPlace","POST" );
			
		}
	}
}
