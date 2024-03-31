package EndtoEndAutomation;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJODeSerialize.CreateOrderResponse;
import POJODeSerialize.LoginResponse;
import POJODeSerialize.orders;
import POJODeSerialize.productResponse;
import POJOSerialize.LoginRequest;
import POJOSerialize.OrderDetails;
import POJOSerialize.Orders;
import io.restassured.specification.RequestSpecification;

public class eCommerceAPITest {

	public static void main(String[] args) {
         
		
		RequestSpecification reqBuild = new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON)
		.build();
		
		//Creating POJO Classes for JSON Body/Payload which we call as Serialization
		LoginRequest login = new LoginRequest();
		login.setUserEmail("DavidMDiaz@rhyta.com");
		login.setUserPassword("David@54321");
		
        RequestSpecification loginrequest = given().log().all().spec(reqBuild).body(login);
		
        
        //Creating and Passing POJO class as argument in response which we call as Deserialization
        
        LoginResponse loginresponse = loginrequest.when().log().all().post("/api/ecom/auth/login").then().log().all().extract().as(LoginResponse.class);
        
        //token
        System.out.println(loginresponse.getToken());
        //UserID
        System.out.println(loginresponse.getUserId());
        //message
        System.out.println(loginresponse.getMessage());
        
        
        
        
        //	Create Product or Add Product
        
        RequestSpecification createProductReq = new RequestSpecBuilder()
        		.setBaseUri("https://rahulshettyacademy.com")
        		.addHeader("Authorization", loginresponse.getToken())
        		.build();
        
        RequestSpecification createProductRequest = given().log().all()
        .spec(createProductReq)
        .param("productName", "qwerty")
        .param("productAddedBy", loginresponse.getUserId())
        .param("productCategory", "fashion")
        .param("productSubCategory", "shirts")
        .param("productPrice", "11500")
        .param("productDescription", "Addidas Originals")
        .param("productFor", "men")
        .multiPart("productImage",new File("C:\\Users\\91807\\Downloads\\addidas.png"));
        
        //Create POJO class to capture the response
        
        productResponse createProductResponse = createProductRequest.when().log().all().post("/api/ecom/product/add-product").then().log().all().extract().as(productResponse.class);
        
        //product ID
        String productId = createProductResponse.getProductId();
        System.out.println(createProductResponse.getProductId());
        
        //Product Message
        System.out.println(createProductResponse.getMessage());
        
        
        
        // Create Order
        
        RequestSpecification createOrderReq = new RequestSpecBuilder()
        		.setBaseUri("https://rahulshettyacademy.com")
        		.setContentType(ContentType.JSON)
        		.addHeader("Authorization", loginresponse.getToken())
        		.build();
        
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderedId(createProductResponse.getProductId());
        
        
        List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
        orderDetailList.add(orderDetails);
        
        Orders order = new Orders();
        order.setOrders(orderDetailList);
        
        RequestSpecification createOrderRequest = given().log().all().spec(createOrderReq).body(order);
        
        String orderResponse = createOrderRequest.when().log().all().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
        
        System.out.println(orderResponse);
        
        
        System.out.println(productId);
        
        //Delete Product
        
        RequestSpecification deleteOrderBuild = new RequestSpecBuilder()
        		.setBaseUri("https://rahulshettyacademy.com/")
        		.addPathParam("productId",productId)
        		.addHeader("Authorization", loginresponse.getToken())
        		.build();
        
        RequestSpecification deleteRequest = given().log().all().spec(deleteOrderBuild);
        
        
        Response response = deleteRequest.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response();
         
        String respo = response.asString();
        JsonPath js1 = new JsonPath(respo);
        String Actual_message = js1.get("message");
        
       Assert.assertEquals("Product Deleted Successfully", Actual_message);
        
        
        
        
        
        
	}

}
