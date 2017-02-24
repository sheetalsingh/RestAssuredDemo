package test;

import org.testng.annotations.*;
import data.DataProviderClass;
import helper.ReadPropertyFile;
import helper.RestMe;
//import io.restassured.RestAssured;
import io.restassured.response.Response;
//import static io.restassured.RestAssured.*;

import java.util.Arrays;
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
	String whatToRun;
	
	@BeforeSuite
	public void setup(){
		readPropFile = new ReadPropertyFile();
		propertyMap = readPropFile.getMap();
		restme =  new RestMe(propertyMap);
		whatToRun = propertyMap.get("whattorun");	
	}
	
	
	@AfterSuite
	public void tearDown(){
		
	}
	
	
	
	/**
	 * csv format: test_id,test_step,suite,action,data(url-path#content-type#status-code#list_of_parameters_pipe_separated)
	 * 
	 * 
	 * This method will get data from Data Provider for all the cases present in csv file
	 * Based on 'whattorun' value in config.properties, performAction() method will be called
	 */
	@Test(dataProvider = "csvdataprovider",dataProviderClass=DataProviderClass.class)
	public void testRequest(String test_id, String test_step, String suite, String action, String data){
		
		if(whatToRun.equals("suite")){
			String [] testsuitestorun = propertyMap.get("testsuitestorun").split(",");
			boolean isRunSuitePresent = Arrays.asList(testsuitestorun).contains(suite);
			if(isRunSuitePresent){
				performAction(action, data);
			}else{
				System.out.println("Dont report this @test");
			}
			
		}else if (whatToRun.equals("testids")) {
			String [] testidstorun = propertyMap.get("testidstorun").split(",");
			boolean isRunTestIdPresent = Arrays.asList(testidstorun).contains(test_id);
			if(isRunTestIdPresent){
				performAction(action, data);
			}else{
				System.out.println("Dont report this @test");
			}
			
		}else{
			performAction(action, data);
		}
	}
	
	
	
	/**
	 * This method get action,data values from @Test testRequest method 
	 * and perform actual call to endpoints
	 * 
	 * @param action : GET, POST etc
	 * @param data: url, status code, parameters etc
	 */
	public void performAction(String action, String data){
		
		switch (action.toUpperCase()) {
		case "GET": restme.getMe(data);break;
		case "POST": restme.postMe(data);break;
		case "DELETE":restme.deleteMe(data); break;
		default: throw new IllegalStateException("Given action name(http methods) in csv are not matching with any of the existing action name");
		}
	
	}
	
	
	
		
}


