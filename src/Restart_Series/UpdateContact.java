package Restart_Series;

import static io.restassured.RestAssured.given;

import files.payload;
import io.restassured.RestAssured;

public class UpdateContact {

	public static void main(String[] args) {
			
		 RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

	        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmZmODE0NGI3ZDk5MDAwMTM4OTk4ZTYiLCJpYXQiOjE3MjgwMjA4MDR9.k-9kmXlFKmGzPWwAbg97-ZYS2t31GZ-Zie5h8oO2kZQ";
           
	        String id = "66ff854ab7d99000138998f6";
	        
	        given().log().all()
	            .header("Authorization", "Bearer " + token)
	            .contentType("application/json")
	            .body(payload.updateContacts())
	        .when()
	            .put("/contacts/"+id+"/")
	        .then()
	            .log().all()
	            .assertThat()
	            .statusCode(200);
	}

}
