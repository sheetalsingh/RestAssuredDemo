package data;

import org.testng.annotations.DataProvider;

/**
 * This class contains different type of Data Providers 
 * e.g. csv data provider, excel data provider etc	
 * 
 * @author sheetalsingh
 */
public class DataProviderClass {

	/**
	 * to get csv data
	 * @return
	 */
	@DataProvider(name = "getdatafromcsv")
	public static Object[][] getDataFromCSV(){
		return CSVDataProvider.getCSVData("inputdata.csv");
	}
		
	/**
	 * todo: dummy excel data 
	 * @return
	 */
	@DataProvider(name = "getdatafromexcel")
	public static Object[][] getDataFromExcel(){
		return CSVDataProvider.getCSVData("dummyexcelfile.xls");
	}
	
	
	
}
