package resourses;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import config.ReadConfig;
import config.Settings;

public class Utils {
	public static RequestSpecification req;
	public ResponseSpecification resspec;	
	public static RequestSpecification requestSpecification() throws IOException {
		ReadConfig.PopulateSettings();
		PrintStream stream = new PrintStream(new FileOutputStream("Logdata1.txt"));
		
		if (req==null) {
		req= new RequestSpecBuilder().setBaseUri(Settings.baseURI).addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.setContentType(ContentType.JSON).build();
		
		return req;
	}
		return req;
	}
	
	public ResponseSpecification  resposeSpecification() throws FileNotFoundException{
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		return resspec;
	}
	
	public static  String getJsonPath(Response response,String key) {
	     	String resp= response.asString();
	     	JsonPath js = new JsonPath(resp);
	     	 return  js.get(key);
	}
	

}
