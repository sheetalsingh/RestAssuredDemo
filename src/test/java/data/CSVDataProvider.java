package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.csvreader.CsvReader;

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
	 * This method will return csv file path
	 * @param fileName
	 * @return
	 */
	private static String getPath(String fileName){
		return System.getProperty("user.dir")+"/src/test/resources/"+fileName;
	}
	
	
	/**
	 * This method will read csv and populate Object[][] 2D array 
	 * @param fileName
	 */
	private static void getData(String fileName){
		int i = 0;
		try{
			data = new Object[2][5]; // row,column
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
		
	}
	
	
	public static Object[][] getCSVData(String fileName){
		getData(fileName);
		return data;
	}
	
}
