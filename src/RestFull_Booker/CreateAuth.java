package RestFull_Booker;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.RestfulBooker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;


public class CreateAuth{
     
	@Test
	public void createAuthToken() {
		RequestSpecification spec = new RequestSpecBuilder()
		.setBaseUri("https://restful-booker.herokuapp.com")
		.setContentType("application/json")
		.build();
		
		String response = given().spec(spec).log().all().body(RestfulBooker.CreateAuthPayload())
		.when().log().all().post("/auth")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String token = js.getString("token");
	  
		Assert.assertEquals(token.isBlank(), false,"Token is Empty");
	}
	    
}
