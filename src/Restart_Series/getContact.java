package Restart_Series;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class getContact {
	
	public static String first_id;

	public static void main(String[] args) {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmZmODE0NGI3ZDk5MDAwMTM4OTk4ZTYiLCJpYXQiOjE3MjgwMjA4MDR9.k-9kmXlFKmGzPWwAbg97-ZYS2t31GZ-Zie5h8oO2kZQ";

		String response = given().log().all().header("Authorization", "Bearer " + token).contentType("application/json").when()
				.get("/contacts").then().log().all().statusCode(200).extract().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String id = js.getString("_id");
		System.out.println(id);
       
		String[] ids = id.replaceAll("[\\[\\]\\]]","").split(", ");
		
		
		//fetch first id
		first_id = ids[0];
		
		System.out.println(first_id);
	
	}

}
