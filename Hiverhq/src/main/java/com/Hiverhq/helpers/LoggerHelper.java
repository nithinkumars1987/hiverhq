package com.Hiverhq.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerHelper {

    private static boolean root = false;

    public static Logger getLogger(Class<?> clasz) {
        if (root) {
            return LogManager.getLogger(clasz);
        }
        //PropertyConfigurator.configure(Resources.getResourcePath("\\src\\main\\resources\\")+"log4j.properties");
        root = true;
        return LogManager.getLogger(clasz);
    }

}
