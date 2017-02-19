package test;

import org.testng.annotations.*;
import data.DataProviderClass;
import helper.ReadPropertyFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Map;

public class DemoTest {
	Response response;
	String jsonString;
	ReadPropertyFile readPropFile;
	Map<String, String> property;
	String headerkey1,headervalue1,headerkey2,headervalue2;
	
	
	@BeforeSuite
	public void setup(){
		readPropFile = new ReadPropertyFile();
		property = readPropFile.getMap();
		
		//initialize base uri and headers values
		RestAssured.baseURI = property.get("baseuri") + property.get("basepath");
		headerkey1 = property.get("headerkey1");
		headervalue1 = property.get("headervalue1");
		headerkey2 = property.get("headerkey2");
		headervalue2 = property.get("headervalue2");
			
	}
	
	
	@AfterSuite
	public void tearDown(){
		
	}
	
	
	/**
	 * This method will verify expected status code and content type (both coming from csv file)
	 * similarly other variable can also be passed from csv and can be verified here
	 */
	//@Test(dataProvider = "getdatafromcsv",dataProviderClass=DataProviderClass.class)
	public void testGetRequest(String endpoint, String paramKey, String paramValue, String contentType, String statusCode){
		
		response=
				given().
					headers(headerkey1, headervalue1).
					headers(headerkey2, headervalue2).
					param(paramKey,paramValue).
				when().
					get(endpoint).
				then().
					statusCode(Integer.parseInt(statusCode)).
					contentType(contentType).
				extract().
					response();
	}
	
	
	
	/**
	 * test_id,test_step,suite,action,data(url-path#content-type#status-code#list_of_parameters_pipe_separated)
	 */
	@Test(dataProvider = "getdatafromcsv",dataProviderClass=DataProviderClass.class)
	public void testGetRequestNew(String test_id, String test_step, String suite, String action, String data){
		
		
		System.out.println(">"+test_id);
		System.out.println(">"+test_step);
		System.out.println(">"+suite);
		System.out.println(">"+action);
		System.out.println(">"+data);
		
		
	}
		
}
