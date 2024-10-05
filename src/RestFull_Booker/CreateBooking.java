package RestFull_Booker;

import static io.restassured.RestAssured.given;

import files.RestfulBooker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;

public class CreateBooking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		
		String BookingResponse = given().log().all().header("Content-Type","application/json").body(RestfulBooker.CreateBookingPayload())
		.when().log().all().post("/booking")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		
		System.out.println(BookingResponse);
		

	}

}
