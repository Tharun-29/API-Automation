package Restart_Series;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class AddContact {

	public static void main(String[] args) {
	   
		RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
		
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmZmODE0NGI3ZDk5MDAwMTM4OTk4ZTYiLCJpYXQiOjE3MjgwMjA4MDR9.k-9kmXlFKmGzPWwAbg97-ZYS2t31GZ-Zie5h8oO2kZQ";
		
        given().log().all().header("Authorization","Bearer "+ token).contentType("application/json").body(payload.addContact())
        .when().post("/contacts")
        .then().log().all().assertThat().statusCode(201).assertThat().body("firstName",equalTo("John"));
 
	}

}
