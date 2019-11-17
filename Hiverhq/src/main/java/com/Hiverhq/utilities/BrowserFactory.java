package com.Hiverhq.utilities;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class BrowserFactory{
	public static final Logger log = LogManager.getLogger(BrowserFactory.class.getName());
	protected static WebDriver driver ;
	
	public static WebDriver getBrowser(String runmode, String browser) {
		if(runmode.equalsIgnoreCase("local")) {
			String os=System.getProperty("os.name").toLowerCase();
			if(os.contains("windows")) {
				if(browser.equalsIgnoreCase("Chrome")) {
					System.setProperty("webdriver.chrome.driver", "./BrowserExtension/Drivers/chromedriver.exe");
					driver = new ChromeDriver();
					log.info("Opening browser : "+browser);
				}
				if(browser.equalsIgnoreCase("Firefox")) {
					System.setProperty("webdriver.gecko.driver", "./BrowserExtension/Drivers/geckodriver.exe");
					driver =new FirefoxDriver();
					log.info("Opening browser : "+browser);
				}
				if(browser.equalsIgnoreCase("Phantomjs")) {
					System.setProperty("phantomjs.binary.path", "./BrowserExtension/Drivers/phantomjs.exe");
					driver =new PhantomJSDriver();
					log.info("Opening browser : "+browser);
				}
				if(browser.equalsIgnoreCase("Chromeheadless")) {
					System.setProperty("webdriver.chrome.driver", "./BrowserExtension/Drivers/chromedriver.exe");
					ChromeOptions options = new ChromeOptions();
	                options.addArguments("headless");
	                options.addArguments("window-size=1200x600");
					driver = new ChromeDriver(options);
					log.info("Opening browser : "+browser);
				}
			}
			else if(os.contains("mac")){
				if(browser.equalsIgnoreCase("Chrome")) {
					System.setProperty("webdriver.chrome.driver", "./BrowserExtension/Drivers/chromedriver");
					 ChromeOptions coptions = new ChromeOptions();
					 coptions.addArguments("--disable-notifications");
					driver = new ChromeDriver(coptions);
					log.info("Opening browser : "+browser);
				}
				if(browser.equalsIgnoreCase("Firefox")) {
					System.setProperty("webdriver.gecko.driver", "./BrowserExtension/Drivers/geckodriver");
					driver =new FirefoxDriver();
					log.info("Opening browser : "+browser);
				}
				if(browser.equalsIgnoreCase("Chromeheadless")) {
					System.setProperty("webdriver.chrome.driver", "./BrowserExtension/Drivers/chromedriver");
					ChromeOptions options = new ChromeOptions();
	                options.addArguments("headless");
	                options.addArguments("window-size=1200x600");
					driver = new ChromeDriver(options);
					log.info("Opening browser : "+browser);
				}
			}
			else {
				if(browser.equalsIgnoreCase("Chrome")) {
					System.setProperty("webdriver.chrome.driver", "./BrowserExtension/Drivers/chromedriver_linux");
					driver = new ChromeDriver();
					log.info("Opening browser : "+browser);
				}
				if(browser.equalsIgnoreCase("Firefox")) {
					System.setProperty("webdriver.gecko.driver", "./BrowserExtension/Drivers/geckodriver_linux");
					driver =new FirefoxDriver();
					log.info("Opening browser : "+browser);
				}
				if(browser.equalsIgnoreCase("Chromeheadless")) {
					System.setProperty("webdriver.chrome.driver", "./BrowserExtension/Drivers/chromedriver_linux");
					ChromeOptions options = new ChromeOptions();
	                options.addArguments("headless");
	                options.addArguments("window-size=1200x600");
					driver = new ChromeDriver(options);
					log.info("Opening browser : "+browser);
				}
				
			}
			
		}
		if(runmode.equalsIgnoreCase("remote")) {
			if(browser.equalsIgnoreCase("Chrome")) {
			    ChromeOptions coptions = new ChromeOptions();
			    coptions.addArguments("--disable-notifications");
			    driver = new ChromeDriver(coptions);
			}
			if(browser.equalsIgnoreCase("Firefox")) {
				FirefoxOptions opt = new FirefoxOptions();
/*				String node = CommonUtils.getPropertyValue("node1");
				System.out.println(node);
				driver = new RemoteWebDriver(new URL(node),opt);*/
			}
		}
		return driver;
	}
	
	public static void openurl(String url) {
		driver.get(url);
		//driver.manage().window().maximize();;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
