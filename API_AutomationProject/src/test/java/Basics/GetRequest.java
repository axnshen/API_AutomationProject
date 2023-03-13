package Basics;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
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
		
		System.out.println("Status code of the response is "+res.getStatusCode());
		System.out.println("Time taken to get the response "+res.getTime()+"mili seconds");
		
		JsonPath jp =  res.jsonPath();
		
		int pageNo = jp.getInt("page");
		System.out.println("Page no. is "+pageNo);
		String email = jp.getString("data[4].email");
		System.out.println("Email id is "+email);
		
		int dataSize = jp.getInt("data.size()");
		System.out.println(dataSize);
		
		for(int i = 0 ; i < dataSize ; i++ ) 
		{
			String fname = jp.getString("data["+i+"].email");
			System.out.println(fname);
		}
	}

}
