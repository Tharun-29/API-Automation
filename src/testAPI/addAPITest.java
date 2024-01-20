package testAPI;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import files.payload;

public class addAPITest {

	public static void main(String[] args) {
		// To Validate if add API is working as expected

		// RestAssured works based on 3 methods
		// 1. given - Will take all the input details
		// 2. when - Submit the API (Method Type and Resource will be mentioned while
		// writing when method)
		// 3. then - validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)");
		
		

	}

}
