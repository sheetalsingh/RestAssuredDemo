package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.csvreader.CsvReader;

import helper.Constants;

/**
 * This is only to read CSV data
 * http://javacsv.sourceforge.net/com/csvreader/CsvReader.html
 * 
 * Alternate: We can user Java opencsv as well
 * http://opencsv.sourceforge.net/apidocs/com/opencsv/CSVReader.html#skipLines
 * 
 * @author sheetalsingh
 *
 */
public class CSVDataProvider {

	private static CsvReader reader = null;
	private static Object[][] data = null;

	/**
	 * This method will return test case file path i.e. src/test/resources/inputdata.csv
	 */
	private static String getPath(String fileName) {
		String path = System.getProperty("user.dir") + Constants.TEST_CASES_PATH;
		String absolutePath = path + fileName;
		System.out.println("Input test case file path: "+absolutePath);
		return absolutePath;
	}

	
	
	
	/**
	 * This method will give row count in a file, after skipping header
	 */
	private static int getRowCount(String fileName) {
		int row = 0;
		try {
			final Path path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
			row = (int) Files.lines(path).skip(1).count();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return row;
	}

	
	

	/**
	 * 	This method will read csv's five column data and give back [rows][5] 2D array
	 *  columns : test_id,test_step,suite,action,data
	 */
	public static Object[][] getCSVData(String fileName) {
		int i = 0;
		try {
			data = new Object[getRowCount(fileName)][5]; // row,column
			reader = new CsvReader(getPath(fileName));
			reader.skipRecord(); // it will skip header line as reader obj contains complete csv data, skipLine() can also be used

			while (reader.readRecord()) {
				data[i][0] = reader.get(0);
				data[i][1] = reader.get(1);
				data[i][2] = reader.get(2);
				data[i][3] = reader.get(3);
				data[i][4] = reader.get(4);
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
