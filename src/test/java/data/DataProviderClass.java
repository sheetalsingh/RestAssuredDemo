package data;

import org.testng.annotations.DataProvider;

import helper.ReadPropertyFile;

/**
 * This class contains different type of Data Providers 
 * e.g. csv data provider, excel data provider etc	
 * 
 * test case to be run will be picked from config.properties file
 * 
 * @author sheetalsingh
 */
public class DataProviderClass {

	ReadPropertyFile readPropertyFile;
	static String testcasedata;
	
	/**
	 * to get csv data
	 * @return
	 */
	@DataProvider(name = "csvdataprovider")
	public static Object[][] getDataFromCSV(){
		testcasedata = new ReadPropertyFile().getMap().get("testcasecsv");
		return CSVDataProvider.getCSVData(testcasedata);
	}
		
	
	
	/**
	 * todo: dummy excel data 
	 * @return
	 */
	@DataProvider(name = "excelldataprovider")
	public static Object[][] getDataFromExcel(){
		return CSVDataProvider.getCSVData("dummyexcelfile.xls");
	}
	
	
	
}
