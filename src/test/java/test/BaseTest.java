package test;

import org.testng.annotations.*;
import data.DataProviderClass;
import helper.ReadPropertyFile;
import helper.RestMe;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Map;

/**
 * 
 *	assumptions:
 *	headers will remain fixed for all calls
 */
public class BaseTest {
	RestMe restme;
	Response response;
	String jsonString;
	ReadPropertyFile readPropFile;
	Map<String, String> propertyMap;
	String headerkey1,headervalue1,headerkey2,headervalue2; 
	
	
	@BeforeSuite
	public void setup(){
		readPropFile = new ReadPropertyFile();
		propertyMap = readPropFile.getMap();
		restme =  new RestMe(propertyMap);
		
		
		//initialize base uri and headers values
//		RestAssured.baseURI = propertyMap.get("baseuri") + propertyMap.get("basepath");
//		headerkey1 = propertyMap.get("headerkey1");
//		headervalue1 = propertyMap.get("headervalue1");
//		headerkey2 = propertyMap.get("headerkey2");
//		headervalue2 = propertyMap.get("headervalue2");			
	}
	
	
	@AfterSuite
	public void tearDown(){
		
	}
	
	
	
	/**
	 * test_id,test_step,suite,action,data(url-path#content-type#status-code#list_of_parameters_pipe_separated)
	 */
	@Test(dataProvider = "csvdataprovider",dataProviderClass=DataProviderClass.class)
	public void testGetRequestNew(String test_id, String test_step, String suite, String action, String data){
		
		switch (action.toUpperCase()) {
			case "GET": restme.getMe(data);break;
			case "POST": restme.postMe(data);break;
			case "DELETE":restme.deleteMe(data); break;
			default: throw new IllegalStateException("Given action name(http methods) in csv are not matching with any of the existing action name");
		}
		
		
	}
	
	
	
	
	
	
		
}








//@Test(dataProvider = "csvdataprovider",dataProviderClass=DataProviderClass.class)
//public void testGetRequest(String endpoint, String paramKey, String paramValue, String contentType, String statusCode){
//	
//	response=
//			given().
//				headers(headerkey1, headervalue1).
//				headers(headerkey2, headervalue2).
//				param(paramKey,paramValue).
//			when().
//				get(endpoint).
//			then().
//				statusCode(Integer.parseInt(statusCode)).
//				contentType(contentType).
//			extract().
//				response();
//}
