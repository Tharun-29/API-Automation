package testAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import files.reusableJSMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class LibraryAPI {

	@Test(dataProvider = "TestData")
	public void addBook(String isbn,String aisle) {

		// Provide the BaseURI / URL Details
		RestAssured.baseURI = "http://216.10.245.166";

		// Create Post Request to Add Books
		String response = given().log().all().header("Content-Type", "application/json")
				.body(payload.addBooks(isbn,aisle)).when().post("/Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asString();

		// Used reuse method from payload by providing API response fetched from
		// previous line as parameter to the method
		JsonPath reuse = reusableJSMethod.reuse(response);
		String ID = reuse.get("ID");
		System.out.println(ID);
	}
	
	//Using DataProvide from TestNG to provide Data to the API Dynamically
    
	@DataProvider(name = "TestData")
	public Object[][] getData() {
         
		//array = Collection of Elements
		//Multi Dimensional Array = Collection of Arrays
		return new Object[][] {{"fdsfa","22332"},{"jgsa","8954"},{"jkgx","645"},{"msz","354"}};
	}
}
