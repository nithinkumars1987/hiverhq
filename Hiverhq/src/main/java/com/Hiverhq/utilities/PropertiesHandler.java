package com.Hiverhq.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import com.Hiverhq.helpers.LoggerHelper;


public class PropertiesHandler {
	private static PropertiesHandler propHandlerObj;
	private static Logger log = LoggerHelper.getLogger(PropertiesHandler.class);

	private static Properties properties;
	
	private  PropertiesHandler() {
	}
	
	public Properties getProperties(String fileName) {
		
		properties = new Properties();
		String configFilePath = Constants.RESOURCE_BASE_PATH + File.separator + fileName+".properties";
		try (InputStream input = new FileInputStream(new File(configFilePath))) {
			properties.load(input);
		} catch (IOException e) {
			log.error("IOException caught while reading environment property..." + e.getStackTrace());
			throw new RuntimeException("Exception caught on reading environment property file..." + e.getStackTrace());
		}
		return properties;
	}
	
	public static PropertiesHandler getPropertyHandlerInstance() {
		if (propHandlerObj == null) {
			propHandlerObj = new PropertiesHandler();
		}
		return propHandlerObj;
	}

}
