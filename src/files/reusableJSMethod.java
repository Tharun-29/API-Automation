package files;

import io.restassured.path.json.JsonPath;

public class reusableJSMethod {
   
	public static JsonPath reuse(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}
}
