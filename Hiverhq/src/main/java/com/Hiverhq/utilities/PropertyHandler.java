package com.Hiverhq.utilities;

import org.apache.logging.log4j.Logger;

import com.Hiverhq.helpers.LoggerHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {

	private static PropertyHandler propHandlerObj;
	private static Logger log = LoggerHelper.getLogger(PropertyHandler.class);

	private Properties properties;

    private String urlFlipkart;
    private String urlAmazon;
    private String productName;
    
    private static String environment;
	static {
        environment = System.getProperty("environment");
        if (environment == null || environment.equals("")) {
            environment = Constants.DEFAULT_ENVIRONMENT;
            log.info("Default environemnt selected for test is : " + environment
                    + "\nIf you would like to change the environment, run with : 'mvn test -P<profile> -DsuiteXmlFile=<xmlFileName> -Dapp.env=<environmentname>'");
        } else {
            log.info("Environemnt selected for test is : " + environment);
        }
    }

    private PropertyHandler() {
        properties = new Properties();
        String configFilePath = Constants.RESOURCE_BASE_PATH + environment + File.separator + "config.properties";
        try (InputStream input = new FileInputStream(new File(configFilePath))) {
            properties.load(input);

            urlFlipkart = properties.getProperty("flipkart.url");
            urlAmazon = properties.getProperty("amazon.url");
            productName = properties.getProperty("product.name");

        } catch (IOException e) {
            log.error("IOException caught while reading environment property..." + e.getStackTrace());
            throw new RuntimeException("Exception caught on reading environment property file..." + e.getStackTrace());
        }
    }


    public static PropertyHandler getPropertyHandlerInstance() {
        if (propHandlerObj == null) {
            propHandlerObj = new PropertyHandler();
        }
        return propHandlerObj;
    }

    public String getUrlFlipkart() {
        return urlFlipkart;
    }
    
    public String getUrlAmazon() {
        return urlAmazon;
    }
    
    public String getProductName() {
        return productName;
    }
	
}