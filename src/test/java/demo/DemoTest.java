package demo;

import org.testng.annotations.*;
import data.DataProviderClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class DemoTest {
	Response response;
	String jsonString;
	
	@BeforeSuite
	public void setup(){
		RestAssured.baseURI = "https://api.fonts.com/rest";
	}
	
	
	@AfterSuite
	public void tearDown(){
		
	}
	
	
	/**
	 * This method will verify expected status code and content type (both coming from csv file)
	 * similarly other variable can also be passed from csv and can be verified here
	 */
	@Test(dataProvider = "getdatafromcsv",dataProviderClass=DataProviderClass.class)
	public void testGetRequest(String endpoint, String paramKey, String paramValue, String contentType, String statusCode){
		
		response=
				given().
					headers("Appkey", "9336244a-2a0c-4e50-bfaf-fabba2e7134e").
					headers("Password", "deepak@123").
					param(paramKey,paramValue).
				when().
					get(endpoint).
				then().
					statusCode(Integer.parseInt(statusCode)).
					contentType(contentType).
				extract().
					response();
	}
		
}
