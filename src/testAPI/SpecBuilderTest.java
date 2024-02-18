package testAPI;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import javax.tools.DocumentationTool.Location;

import POJOSerialize.addGoogleMaps;
import POJOSerialize.locations;
public class SpecBuilderTest {

	public static void main(String[] args) {
	 	
	// Creating object of request spec builder
	
	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
	.setContentType(ContentType.JSON).build();
	
	
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
	
	// Provide the request spec builder created
	// Provide the addGoogleMaps object input in the body for input request payload
	RequestSpecification res = given().spec(req).body(gm);
	
	
	// Creating object of Response Spec builder
	ResponseSpecification respon = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	Response response = res.when().post("/maps/api/place/add/json")
	.then().spec(respon).extract().response();
	
	String result = response.asString();
	System.out.println(result);
	
}
}
