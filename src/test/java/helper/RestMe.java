package helper;

import static io.restassured.RestAssured.given;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * 
 * Rest class will contains all GET, POST, DELETE etc methods
 * 
 * @author sheetalsingh
 * https://developer.android.com/reference/org/json/JSONObject.html
 * 
 * todo: handle xml object, we can return common object for both type
 */
public class RestMe {

	//JSONObject jsonobject;
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	
	ReadPropertyFile readPropFile;
	Map<String, String> property;
	String headerkey1,headervalue1,headerkey2,headervalue2;

	public RestMe(Map<String, String> propertyMap){
		
		//create base url + read header values
		RestAssured.baseURI = propertyMap.get("baseuri") + propertyMap.get("basepath");
		headerkey1 = propertyMap.get("headerkey1");
		headervalue1 = propertyMap.get("headervalue1");
		headerkey2 = propertyMap.get("headerkey2");
		headervalue2 = propertyMap.get("headervalue2");
		

		//create request obj
		RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
		requestBuilder.addParam(headerkey1, headervalue1);
		requestBuilder.addParam(headerkey2, headervalue2);
		requestSpecification = requestBuilder.build();					
	}
	
	
	
	public void getMe(String data) {
		
		//GET data handling - specific to GET only
		String [] data_array = data.split("#");
		
		String url = data_array[0];
		String content_type = data_array[1];
		int status_code = Integer.parseInt(data_array[2]);
		
		//list contains key:value pairs which further need to split 
		String [] allParamList = data_array[3].split("!");
		
		//param1 contains first key:value pair
		String [] param1 = allParamList[0].split(":");
		String key1 = param1[0];
		String value1= param1[1];
		
		//param2 contains second key:value pair
		String [] param2 = allParamList[1].split(":");
		String key2 = param2[0];
		String value2 = param2[1];

		//expected values checked in response specification
		ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectStatusCode(status_code);
		responseBuilder.expectContentType(content_type);
		responseSpecification = responseBuilder.build();
				
		//GET call
		given().
			spec(requestSpecification).
			param(key1, value1).
			param(key2, value2).
		when().
    		get(url).
    	then().
    		spec(responseSpecification);
    		
 
		
		//extract().response();
		
		//write response in file
//		jsonobject = new JSONObject(response.toString());
//		writeFile("/src/test/resources/output/out.json", jsonobject.toString());
    			
	}

	
	public void postMe(String data) {
		//System.out.println("post:::: "+data);
	}

	
	public void deleteMe(String data) {

	}
	
	
	
	public void writeFile(String path, String content) {
		try {

			File file = new File(path);
			file.getParentFile().mkdirs();
			file.createNewFile();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"));
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
