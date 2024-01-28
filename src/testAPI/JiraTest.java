package testAPI;
import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
public class JiraTest {



	public static void main(String[] args) {
		/*
		 * Steps to Automate in JIRA Application:
		 * 
		 * 1. Login to Jira to Create Session using Login API
		 * 2. Add a comment to existing Issue using Add Comment API
		 * 3. Add an attachment to existing Issue using Add Attachment API
		 * 4. Get Issue details and verify if added comment and attachment exists using Get Issue API
		 */
		RestAssured.baseURI="http://localhost:8080/";
		
		//Create a new session using the Jira REST API
		//SessionFilter is a class which automatically listens the session response of an API
		//A session filter can be used record the session id returned from the server as well as automatically apply this session id in subsequent requests.
		SessionFilter session = new SessionFilter();
		
		String addCommentMessage = "Rest API Testing is beautiful and intersting when understood correctly";
		
		String responseString = given().header("Content-Type", "application/json").body("{ \"username\": \"Tharun\", \"password\": \"Tharun@29012000\" }").log().all()
		.filter(session)
		.when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		//JIRA API to Add a comment (Refer JIRA API Page for Contract details- https://docs.atlassian.com/software/jira/docs/api/REST/7.6.1/#api/2/issue-addComment)
		//Add a comment
		String commentApiResult = given().pathParam("key", "AUT-1").log().all().header("Content-Type", "application/json").body("{\r\n"
				+ "    \"body\": \""+addCommentMessage+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}")
		//filter method added below
		.filter(session)
		.when().post("http://localhost:8080/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(commentApiResult);
		String commentId = js.getString("id");
		
		//Add attachments (Refer JIRA API Page for contract details - https://docs.atlassian.com/software/jira/docs/api/REST/7.6.1/#api/2/issue/{issueIdOrKey}/attachments-addAttachment)
		given().pathParam("key", "AUT-1").header("X-Atlassian-Token","no-check").log().all()
		.filter(session)
		.header("Content-Type","multipart/form-data")
		
		//Multipart requests are a type of HTTP POST request. They allow sending various files or data in a single request.
		.multiPart("file",new File("testJiraAttachments.txt"))
		.when().post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
		
		
		//Get issue details
		String issueDetails = given().filter(session).pathParam("key", "AUT-1")
		.queryParam("fields", "comment")		
		.log().all()
		.when().get("/rest/api/2/issue/{key}").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(issueDetails);
		
		JsonPath js1 = new JsonPath(issueDetails);
		int commentsCount = js1.getInt("fields.comment.comments.size()");
		for(int i=0;i<commentsCount;i++) 
		{
			String actualCommentId = js1.get("fields.comment.comments["+i+"].id").toString();
			if(actualCommentId.equalsIgnoreCase(commentId)) {
			   String commentBody = js1.get("fields.comment.comments["+i+"].body").toString();
			   System.out.println(commentBody);
			   Assert.assertEquals(addCommentMessage,commentBody);
			}			
		}
	}

}
