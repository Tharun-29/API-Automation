package testAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJODeSerialize.Courses;
import POJODeSerialize.GetCourse;
import POJODeSerialize.api;
import POJODeSerialize.webAutomation;
public class oAuthTest {

	public static void main(String[] args) {
		
		
		
		String[] courseDetails = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		// Provide Base URI
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given()
		.formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().log().all()
		.post("oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println("Response is: "+response);
		
		// Now Parsing the previous response to get access Token using JSONPATH
		//
		JsonPath js = new JsonPath(response);
		String access_token_gen = js.getString("access_token");
		
		
		// Triggering Course Details API to fetch details by using access Token from previous autho request call
		
		// DeSerialization done by creating POJO Class, JAVA objects
		
		//POJO - Plain Old Java Object
		GetCourse gc = given().queryParams("access_token",access_token_gen)
		.when().log().all()
		.get("/oauthapi/getCourseDetails").as(GetCourse.class);
		
		//accessing the  needed attribute value from response by creating object of respective java class
	    
		System.out.println(gc.getInstructor());
		System.out.println(gc.getUrl());
		System.out.println(gc.getExpertise());
		System.out.println(gc.getServices());
		System.out.println(gc.getLinkedIn()); 
		
		
		List<api> api = gc.getCourses().getApi();
        int apiSize = api.size();
        for(int i=0;i<apiSize;i++) {
        	if(api.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
        		System.out.println(api.get(i).getCourseTitle());
        		break;
        	}
        }
        
		System.out.println(gc.getCourses().getApi().get(1).getPrice());
		
		// Task - To compare all the course title from Web Automation
		
		List<webAutomation> webauto = gc.getCourses().getWebAutomation();
		
		//Created ArrayList to store all the course title from response
		ArrayList<String> a = new ArrayList<String>();
		for(int i=0;i<webauto.size();i++) {
			a.add(webauto.get(i).getCourseTitle());
		}
		
		//converting string array to array list. Since difficult to compare string array and arraylist. so its easy to compare 2 arraylist
		List<String> expectedCourses = Arrays.asList(courseDetails);
		
		//using Assertion
		Assert.assertTrue(a.equals(expectedCourses));
		
	}

}
