package testAPI;

import java.util.List;

import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {

	public static void main(String[] args) {
		/*
		 * 1. Print No of courses returned by API
		 * 
		 * 2. Print Purchase Amount
		 * 
		 * 3. Print Title of the first course
		 * 
		 * 4. Print All course titles and their respective Prices
		 * 
		 * 5. Print no of copies sold by RPA Course
		 * 
		 * 6. Verify if Sum of all Course prices matches with Purchase Amount
		 * 
		 */
       
		JsonPath js = new JsonPath(payload.coursePrice());
		
		//1. Print No of courses returned by API
		List<Object> totalCourse = js.getList("courses");
		System.out.println(totalCourse.size()); 
		
		//or
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//2. Print Purchase Amount
		int amt = js.getInt("dashboard.purchaseAmount");
		System.out.println(amt);
		
		//3. Print Title of the first course
		String  firstCourseTitle = js.getString("courses[0].title");
		System.out.println(firstCourseTitle);
		
		
		//4. Print All course titles and their respective Prices
		for(int i=0;i<count;i++) {
			String courseTitles = js.getString("courses["+i+"].title");
			int price = js.getInt("courses["+i+"].price");
			System.out.println("Course Title: "+courseTitles+" -> Price of Course:"+price);
		}
      
		//5. Print no of copies sold by RPA Course
		
		int copies = js.getInt("courses[2].copies");
		System.out.println(copies);
		
		//or (Dynamic Approach)
		for(int i=0;i<count;i++) {
			String courseTitles = js.getString("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("Cypress")) {
				int courseCopies = js.getInt("courses["+i+"].copies");
				System.out.println(courseCopies);
				break;
			}
		}
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int totalSum = 0;
		for(int i=0;i<count;i++) {
			int courseCopy = js.getInt("courses["+i+"].copies");
			int coursePrice = js.getInt("courses["+i+"].price");
			totalSum = totalSum + (courseCopy * coursePrice);
		}
		System.out.println(totalSum);
		
		Assert.assertEquals(amt, totalSum);

	}

}
