package resourses;

import io.cucumber.datatable.DataTable;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public static AddPlace addPlacePayload(String name,String website,String language) {
		AddPlace ap = new AddPlace();
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		ap.setAccuracy(30);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 232");
		ap.setAddress("33, side layout, cohen 09");
		String[] type = {"Normal House","Pent House"};
		ap.setTypes(type);
		ap.setWebsite(website);
		ap.setLanguage(language);
		return ap;
	}
	
	public static String deletePlace(String place_id) {
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}\r\n" + 
				"";

	}
}
