package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Class will read and load config.properties into a Map
 * 
 * @author sheetalsingh
 *
 */
public class ReadPropertyFile {

	Properties properties = new Properties();
	Map<String, String> propMap = new HashMap<String, String>();

	/**
	 * Constructor will load Map
	 */
	public ReadPropertyFile() {
		loadHashMapWithPropertyValues();
	}

	
	
	/**
	 * Read config property file and load Map
	 */
	public void loadHashMapWithPropertyValues() {
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(Constants.CONFIG_PROPERTIES_FILE);
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		propMap.putAll(properties.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString())));
	}

	
	
	/**
	 * @return Map
	 */
	public Map<String, String> getMap() {
		return propMap;
	}

}
