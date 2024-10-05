package testAPI;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class practice {

	public static void main(String[] args) {
		
		RestAssured.baseURI="http://216.10.245.166/";
		String response = given().body("{\r\n"
				+ "\r\n"
				+ "\"name\":\"Python\",\r\n"
				+ "\"isbn\":\"abd\",\r\n"
				+ "\"aisle\":\"220\",\r\n"
				+ "\"author\":\"William\"\r\n"
				+ "}\r\n"
				+ "").log().all().
				when().post("/Library/Addbook.php").
				then().log().all().assertThat().statusCode(200)
		.extract().asString();
		System.out.println(response);

	}

}
