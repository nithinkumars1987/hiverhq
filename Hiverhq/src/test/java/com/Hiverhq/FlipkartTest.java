package com.Hiverhq;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;

import com.Hiverhq.helpers.BrowserHelper;
import com.Hiverhq.helpers.WaitHelper;
import com.Hiverhq.utilities.BrowserFactory;
import com.Hiverhq.utilities.PropertyHandler;

public class FlipkartTest {
	
	FlipkartHome objFlipkartHome;
	WebDriver driver;
	public static Logger log = com.Hiverhq.helpers.LoggerHelper.getLogger(FlipkartTest.class);
	
	@Parameters({ "browser", "runmode" })
	@BeforeMethod
	public void beforeMethod(@Optional("Chrome") String browser, @Optional("Local") String runmode)  {
		driver = com.Hiverhq.utilities.BrowserFactory.getBrowser(runmode, browser);
		BrowserFactory.openurl(PropertyHandler.getPropertyHandlerInstance().getUrlFlipkart());
	}
		
		@Test
		public void flipkart()
		{
			objFlipkartHome = PageFactory.initElements(driver, FlipkartHome.class);
			objFlipkartHome.popupclose();
			objFlipkartHome.hoveronproduct();
			objFlipkartHome.clickonmobile();
			objFlipkartHome.clickonfirstmobile();
			objFlipkartHome.clickonaddtocart();
			objFlipkartHome.increasequantity();
			String productprice = objFlipkartHome.getfinalprice();
			log.info("Price is "+productprice);
		}
		
		@AfterMethod
		public void close() {
			driver.quit();
		}


}
