package RestFull_Booker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class GetBooking{

	public static void main(String[] args) {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		
		given().log().all().header("Content-Type","application/json").pathParam("id",336)
		.when().log().all().get("/booking/{id}")
		.then().log().all()
		.statusCode(200)
		.body("firstname", notNullValue()) // Verify that the response contains a "firstname" field
        .body("lastname", notNullValue()) // Verify that the response contains a "lastname" field
        .body("totalprice", notNullValue()) // Verify that the response contains a "totalprice" field
        .body("depositpaid", notNullValue()) // Verify that the response contains a "depositpaid" field
        .body("bookingdates", notNullValue()) // Verify that the response contains a "bookingdates" field
        .body("additionalneeds", notNullValue()); // Verify that the response contains an "additionalneeds" field

	}

}
