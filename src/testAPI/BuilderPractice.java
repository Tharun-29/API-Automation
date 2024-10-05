package testAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class BuilderPractice {
	// To Validate if add API is working as expected

			// RestAssured works based on 3 methods
			// 1. given - Will take all the input details
			// 2. when - Submit the API (Method Type and Resource will be mentioned while
			// writing when method)
			// 3. then - validate the response
	       @Test
           public void TestRunAPI() throws IOException {
	    	   
	    	 
	    	//Declare Request Specification
	    	RequestSpecification requestSpec = new RequestSpecBuilder()
	    			.setBaseUri("https://rahulshettyacademy.com")
	    			.addQueryParam("key", "qaclick123")
	    			.addHeader("Content-Type", "application/json")
	    			.build();
	    	 
			
			
			// Content of the File to String 
			//i.e First Content of the File to be converted into Bytes (Using readAllBytes from Java Files Method)
			// Second -> Provide JSON file path to "Path method"
			// Third -> All the above steps must be wrapped inside String method/constructor to make it String
			//So that the String can be passed in "body method". Body method will accept only string
			
			String apiPath = new String(Files.readAllBytes(Path.of("C:\\Users\\THVS0621\\Videos\\AddPlace.json")));
			
			// Captured Request Details with Request Spec Builder
			RequestSpecification request = given().log().all().spec(requestSpec);
			
			//Declare Response Specification
			
			      ResponseSpecification responseSpec = new ResponseSpecBuilder()
			      .expectStatusCode(200)
			      .expectBody("scope", equalTo("APP"))
			      .expectHeader("server", "Apache/2.4.52 (Ubuntu)")
			      .build();
			 
			      //Utilizing above captured request in below code
				Response response = request.body(apiPath).when().post("/maps/api/place/add/json").then().log().all().spec(responseSpec).extract().response();
                String result = response.asString();
                System.out.println(result);
                
                
          
	       }
}
