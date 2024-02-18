package testAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.payload;
import files.reusableJSMethod;
import files.updatePayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class E2E1 {

	public static void main(String[] args) {
		// Add Place -> Update Place with New Address -> Get Place to Validate if New
		// address is present in response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().asString();

		System.out.println(response);
		

		// To Parse the response Body(Output of API) and get the paramter value for
		// further scenario - you need
		// to use JsonPath

		JsonPath js = reusableJSMethod.reuse(response); // for parsing json
		String place_id = js.getString("place_id");
		System.out.println(place_id);

		// Update Place
		given().log().all().queryParam("key", "qaclick2023").header("Content-Type", "application/json")
				.body(updatePayLoad.updatedPayLoads(place_id)).when().put("/maps/api/place/update/json").then().log()
				.all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		String fetchedaddress = "70 winter walk, USA";

		// Get Place
		String getResponse = given().log().all().queryParam("key","qaclick123")
				.queryParam("place_id", place_id).when().get("/maps/api/place/get/json").then().assertThat().log().all().statusCode(200)
				.extract().asString();

		JsonPath js3 = reusableJSMethod.reuse(getResponse);
		String actualaddress = js3.getString("address");
		System.out.println(fetchedaddress);
		System.out.println(actualaddress);
		
		Assert.assertEquals(fetchedaddress, actualaddress);
		

	}

}
