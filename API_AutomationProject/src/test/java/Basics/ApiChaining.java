package Basics;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiChaining 
{
	static String messagevalue ;
	
	@Test(priority = 1)
	public void createUser()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		Response response = given().body("{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"username\": \"userthree\",\r\n"
				+ "  \"firstName\": \"Bobby\",\r\n"
				+ "  \"lastName\": \"Deol\",\r\n"
				+ "  \"email\": \"d@d1.com\",\r\n"
				+ "  \"password\": \"Test@12345\",\r\n"
				+ "  \"phone\": \"9876543214\",\r\n"
				+ "  \"userStatus\": 0\r\n"
				+ "}")
		
							.header("Content-Type", "application/json")
		
							.when()
		
							.post("/user")
		
							.then()
		
							.extract()
		
							.response();
		
		
		JsonPath jp = response.jsonPath();
		
		 messagevalue = jp.getString("message");
		 
		 System.out.println("message value is : "+messagevalue);
		
		 
		
	}
	


	@Test(priority = 2)
	public void getUserDetails()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		Response resp = given()
				
		.header("Content-Type", "application/json")
		
		.when()
		
		.get("/user/userthree")
		
		.then()
		
		.extract()
		
		.response();
		
		JsonPath jp = resp.jsonPath();
		
		String  idvalue = jp.getString("id");
			
		
		System.out.println("id value is "+ idvalue);
		
		Assert.assertEquals(idvalue, messagevalue);
	}

}
