package testAPI;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class BrushUp {
	

	public static void main(String[] args) {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		given().queryParam("AuthorName", "Milly").headers("Content-Type","application/json").log().all()
		.when().get("/Library/GetBook.php").then().log().all().assertThat().statusCode(200);
		
		given().log().all().body("{\r\n"
				+ "\r\n"
				+ "\"name\":\"RestAssured\",\r\n"
				+ "\"isbn\":\"add\",\r\n"
				+ "\"aisle\":\"124\",\r\n"
				+ "\"author\":\"Milly\"\r\n"
				+ "}\r\n"
				+ "").when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200);
		
		

	}

}
