package helper;

public class Constants {
	
	
	/**
	 * following test case path will be different for Mac/Win/Linux
	 * /src/test/resources/
	 */
	static String fileSeparator = System.getProperty("file.separator");
	public static final String TEST_CASES_PATH = fileSeparator+"src"+fileSeparator+"test"+fileSeparator+"resources"+fileSeparator;

	
	public static final String CONFIG_PROPERTIES_FILE = "config.properties";
	
}
