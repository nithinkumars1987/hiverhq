package com.Hiverhq.helpers;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class WaitHelper {

	private static Logger log = LoggerHelper.getLogger(WaitHelper.class);

	private static long timeOut = 60;
	private static long loaderTimeOut = 180;

	public static WebElement waitForElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		log.info("Waiting for " + element + " to be clickable..");
		WebElement rElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		return rElement;
	}
	
	public static List<WebElement> waitForElements(WebDriver driver, List<WebElement> elements) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		log.info("Waiting for " + elements + " to be clickable..");
		List<WebElement> rElements = wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		return rElements;
	}

	public static WebElement waitForElementVisibility(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		log.info("Waiting for " + element + " to be visibile..");
		WebElement rElement = wait.until(ExpectedConditions.visibilityOf(element));

		return rElement;
	}
	
	public static WebElement fluentWaitForElement(WebDriver driver, WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOut, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		log.info("Waiting for " + element + " to be clickable..");
		WebElement rEeliment = wait.until(ExpectedConditions.elementToBeClickable(element));
		return rEeliment;
	}

	public static void implicitWait(WebDriver driver) {
		log.info("Waiting for implicit wait .." + timeOut);
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}



	public static void sleep(long time) {
		try {
			log.info("Driver sleeping for : "+ time +" Milisecond");
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.warn("InterruptedException caught ..."+e.getMessage());
        }
	}

	
}
