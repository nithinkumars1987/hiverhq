
package com.Hiverhq.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;


public class Common {

	public static Logger log = com.Hiverhq.helpers.LoggerHelper.getLogger(Common.class);

	public static String getCurrentDateTime(String timeFormat, String timeZone) {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat(timeFormat);
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		return (df.format(d));
	}

	// get Date to x days ago
	public static String pushDateToXNoOfDays(String sDate, int x, String dateFormat) {
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat(dateFormat).parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date1);
		cal.add(Calendar.DATE, -x);
		SimpleDateFormat date2 = new SimpleDateFormat(dateFormat);
		return date2.format(cal.getTime());
	}

	// get Date in Milliseconds
	public static String getUpdatedDateTimeinMilliseconds(String sDate) {
		String myDate = sDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(myDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long millis = date.getTime();
		return String.valueOf(millis);
	}

	/*
	 * Millisecond to YYYY-MM-dd
	 */
	public static String getDateMilliToYYYYMMDD(String dateInMilli) {
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		long milliSeconds = Long.parseLong(dateInMilli);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}

	public static String getPropertyValue(String filename, String key) throws IOException {
		FileInputStream fis = new FileInputStream(".\\src\\main\\resources\\staging\\" + filename + ".properties");
		Properties p = new Properties();
		p.load(fis);
		return p.getProperty(key);
	}

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
			File sourceFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
			File destFile = new File(".//screenshots//" + screenshotName
					+ getCurrentDateTime(DateTimeConstants.DATE_FORMAT_UNIQUE, DateTimeConstants.DATE_IN_IST) + ".png");
			FileUtils.copyFile(sourceFile, destFile);
			log.info("Screenshot captured and stored as : " + destFile.getAbsolutePath());

			return destFile.getCanonicalPath();
		} catch (Exception e) {
			log.warn("Exception caught while taking screenshot : " + e);
		}
		return null;
	}


	/**
	 * @Parm StringToReplace
	 * @Return String with single quotes
	 */

	public static String replaceToString(String str) {
		return "'" + str + "'";
	}

	/**
	 * @Parm StringToCheckNull
	 * @Return If Null return default data from property file
	 */
	public static String nullCheck(String str, String key, String fileName) {
		if (str == null || str.equals("")) {
			str = PropertiesHandler.getPropertyHandlerInstance().getProperties(fileName).getProperty(key);
			return str;
		} else {
			return str;
		}
	}

	//Fetch the month on the basis of value passed, like 01 is mapped to january
	public static String monthValue(String value) {
		String month = null;
		switch (value) {
		case "01":
			month = "January";
			break;
		case "02":
			month = "February";
			break;
		case "03":
			month = "March";
			break;
		case "04":
			month = "April";
			break;
		case "05":
			month = "May";
			break;
		case "06":
			month = "June";
			break;
		case "07":
			month = "July";
			break;
		case "08":
			month = "August";
			break;
		case "09":
			month = "September";
			break;
		case "10":
			month = "October";
			break;
		case "11":
			month = "November";
			break;
		case "12":
			month = "December";
			break;

		default:
			System.out.println("Month option not found");
			break;
		}

		return month;
	}

	//check if passed string is null then it returns null else it return the mockUp String passed
	public static String mockUpnullCheck(String str, String mockUpStr) {
		if (str == null) {
			return null;
			}
		 else {
			return Common.replaceToString(mockUpStr);
		}
	}
	
	public static int convertStringToInteger(String number)
	{
		   String  numbervalue="";
		   for(int i=0;i<=number.length()-1;i++)
		   {
			if(number.charAt(i)>='0'&&number.charAt(i)<='9')
			{
				numbervalue=numbervalue+number.charAt(i);
			}
			
		   }
			return Integer.parseInt(numbervalue);

	}
		
	public static String dateToString(Date date, String format, String timeZone) { 

        // create SimpleDateFormat object 
        SimpleDateFormat sdf = new SimpleDateFormat(format); 

        // default system timezone if passed null or empty 
        if (timeZone == null || "".equalsIgnoreCase(timeZone.trim()))
        { 
            timeZone = Calendar.getInstance().getTimeZone().getID(); 
        } 

        // set timezone to SimpleDateFormat 
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone)); 

        // return Date in required format with timezone as String 
        return sdf.format(date); 

    } 
}
