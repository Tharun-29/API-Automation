package RestFull_Booker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class GetBookingIds {
	
	static String BookingIds;

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		
		BookingIds = given().log().all().header("Content-Type","application/json")
		.when().log().all().get("/booking")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(BookingIds);
        
	}

}
