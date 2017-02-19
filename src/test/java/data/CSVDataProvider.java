package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.csvreader.CsvReader;

import helper.Constants;

/**
 * This is only to read CSV data
 * 
 * @author sheetalsingh
 *
 */
public class CSVDataProvider {

	private static CsvReader reader = null;
	private static Object [][] data = null;
	
	
	/**
	 * This method will return test case csv file path
	 * 
	 * file separator used so that same code would work in windows m/c where file separator is \
	 */
	private static String getPath(String fileName){
		String fileSeparator =  System.getProperty("file.separator");
		String path = System.getProperty("user.dir")+Constants.TEST_CASES_PATH.replaceAll("/",fileSeparator);
		return path+fileName;
	}
	
	
//	public static Object[][] getCSVData(String fileName){
//		int i = 0;
//		try{
//			data = new Object[2][5]; // row,column
//			reader = new CsvReader(getPath(fileName));
//			while(reader.readRecord()){
//				data[i][0] = reader.get(0);
//				data[i][1] = reader.get(1);
//				data[i][2] = reader.get(2);
//				data[i][3] = reader.get(3);
//				data[i][4] = reader.get(4);
//				i++;
//				
//			}
//		}catch(FileNotFoundException e){
//			e.printStackTrace();
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return data;
//	}
	
	//test_id,test_step,suite,action,data
	public static Object[][] getCSVData(String fileName){
		
		int rows = 3;
		int i = 0; // ignore headers in file
		try{
			data = new Object[rows][5]; // row,column
			reader = new CsvReader(getPath(fileName));
			while(reader.readRecord()){
				data[i][0] = reader.get(0);
				data[i][1] = reader.get(1);
				data[i][2] = reader.get(2);
				data[i][3] = reader.get(3);
				data[i][4] = reader.get(4);
				i++;
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
		
	}
	
}
