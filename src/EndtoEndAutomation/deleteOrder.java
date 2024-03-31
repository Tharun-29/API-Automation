package EndtoEndAutomation;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class deleteOrder {
  
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response = given().log().all().header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWI0YTY5OGE4NmY4Zjc0ZGM2MzIwNjIiLCJ1c2VyRW1haWwiOiJEYXZpZE1EaWF6QHJoeXRhLmNvbSIsInVzZXJNb2JpbGUiOjc2NDUzNDM0NTYsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE3MDk2NDMwNTEsImV4cCI6MTc0MTIwMDY1MX0.YqX-EXOhmosJJ8yqDuNp2J-xSM83xpbCp_jkATUwNTU")
		.pathParams("productId","65e6e953a86f8f74dc912d71")
		.when()
		.delete("/api/ecom/product/delete-product/{productId}")
		.then().log().all()
		.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		System.out.println(js.getString("message"));
	}
	
}
