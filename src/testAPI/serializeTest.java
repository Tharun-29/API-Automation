package testAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import javax.tools.DocumentationTool.Location;

import POJOSerialize.addGoogleMaps;
import POJOSerialize.locations;
public class serializeTest {

	public static void main(String[] args) {
	  
	  
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
	// Creating Payload request details using created POJO Class and methods - by creating object of that class
	
	addGoogleMaps gm = new addGoogleMaps();
	gm.setAccuracy(50);
	gm.setName("David house");
	gm.setPhone_number("(+91) 983 893 3937");
	gm.setAddress("29, side layout, cohen 09");
	gm.setWebsite("http://google.com");
	gm.setLangugae("French-IN");
	
	//Types object
	List<String> list = new ArrayList<String>();
	list.add("shoe park");
	list.add("shop");
	
	gm.setTypes(list);
		
	//Location object
	locations l = new locations(); 
	l.setLat(-38.383494);
	l.setLng(33.427362);
	
	gm.setLocation(l);
	
	
	// Provide the addGoogleMaps object input in the body for input request payload
	Response response = given().log().all().queryParam("key","qaclick123").body(gm)
	.when().post("/maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200).extract().response();
	
	String res = response.asString();
	System.out.println(res);
	
}
}
