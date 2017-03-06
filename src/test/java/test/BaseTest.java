package test;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import data.DataProviderClass;
import helper.ReadPropertyFile;
import helper.RestMe;
import helper.TestListeners;
//import io.restassured.RestAssured;
import io.restassured.response.Response;
//import static io.restassured.RestAssured.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.util.Arrays;
import java.util.Map;

import javax.xml.ws.RespectBinding;

/**
 *	assumptions:
 *	headers will remain fixed for all calls hence reading from config.properties file
 *
 *	@author sheetalsingh
 *	@date Feb 2017
 */
@Title("Base Test Class")
@Description("Description: Common class for all API tests")
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
	@Features("Test Method")
	@Stories("Data provider Consumer")
	@Severity(SeverityLevel.CRITICAL)
	@Title("API Calls")
	@Description("Description: All types of calls")
	@Test(dataProvider = "csvdataprovider",dataProviderClass=DataProviderClass.class)
	public void testRequest(String test_id, String test_step, String suite, String action, String data){
		
		if(whatToRun.equals("suite")){
			String [] testsuitestorun = propertyMap.get("testsuitestorun").split(",");
			boolean isRunSuitePresent = Arrays.asList(testsuitestorun).contains(suite);
			if(isRunSuitePresent){
				performAction(action, data);
			}else{
				System.out.println("########### Dont report this @test");
				throw new SkipException("SUITE NOT MATCHING.....");
			}
			
		}else if (whatToRun.equals("testids")) {
			String [] testidstorun = propertyMap.get("testidstorun").split(",");
			boolean isRunTestIdPresent = Arrays.asList(testidstorun).contains(test_id);
			if(isRunTestIdPresent){
				performAction(action, data);
			}else{
				System.out.println("########### Dont report this @test");
				throw new SkipException("SUITE NOT MATCHING.....");
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
		case "GET": restme.getMe(data,"token");break;
		case "POST": restme.postMe(data);break;
		case "DELETE":restme.deleteMe(data,"token"); break;
		default: throw new IllegalStateException("Given action name(http methods) in csv are not matching with any of the existing action name");
		}
	
	}
	
		
}


