package Basics;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/*
 * 1. Print No of courses returned by API
	2. Print Purchase Amount
	3. Print Title of the first course
	4. Print All course titles and their respective Prices
	5. Print no of copies sold by RPA Course
 */
public class FetchValueFromJson 
{
	int noOfCourses;
	JsonPath jp;
	@Test(priority=0)
	public void getDetails() 
	{
		jp = new JsonPath("{\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\"website\": \"www.abc.com\"\r\n"
				+ "},\r\n"
				+ "\"courses\": [\r\n"
				+ "{\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\"price\": 50,\r\n"
				+ "\"copies\": 6\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\"price\": 40,\r\n"
				+ "\"copies\": 4\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\"price\": 45,\r\n"
				+ "\"copies\": 10\r\n"
				+ "}\r\n"
				+ "]\r\n"
				+ "}");
		
		// 1. Print No of courses returned by API
		
		noOfCourses = jp.getInt("courses.size()");
		System.out.println("Number of Courses available = "+noOfCourses);
		
		// 2. Print Purchase Amount
		
		int purchaseAmt = jp.getInt("dashboard.purchaseAmount");
		System.out.println("The purchase amount is  "+purchaseAmt);
		
		// 3. Print Title of the first course
		
		String courseTitle = jp.getString("courses[0].title");
		System.out.println(courseTitle);
		
		// 4. Print All course titles and their respective Prices
		
		for(int i = 0 ; i < noOfCourses ; i++) 
		{
			String title = jp.getString("courses["+i+"].title");
			int price = jp.getInt("courses["+i+"].price");
			
			System.out.println("Course Name: "+title+" & Price: "+price);
			
		}
		
		// 5. Print no of copies sold by RPA Course
		
		for(int i = 0 ; i < noOfCourses ; i++) 
		{
			String title = jp.getString("courses["+i+"].title");
			
			if(title.equals("RPA")) 
			{
				int noOfCopies = jp.getInt("courses["+i+"].copies");	
				System.out.println("No of copies sold by RPA Course: "+noOfCopies);
			}
		}
		
	}
	
	@Test(priority=1)
	public void validatePurchaseAmount()
	{
		int total = 0;
		for(int i = 0 ; i < noOfCourses ; i++) 
		{
			int copies = jp.getInt("courses["+i+"].copies");
			int price = jp.getInt("courses["+i+"].price");
			total = total + copies*price;
		}
		System.out.println(total);
		
		int purchaseAmt = jp.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(total, purchaseAmt);
	}



}
