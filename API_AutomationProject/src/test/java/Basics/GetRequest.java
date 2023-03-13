package Basics;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequest 
{
	@Test
	public void getUser() 
	{
		RestAssured.baseURI = "https://reqres.in/";
		Response res 	= 	given()
									.when()
									.get("api/users?page=2")
									.then()
									.extract()
									.response();
		System.out.println(res);
		System.out.println(res.asPrettyString()); //provide the json response in the string format
		
		System.out.println(res.getStatusCode());
		System.out.println(res.getTime());
	}

}
