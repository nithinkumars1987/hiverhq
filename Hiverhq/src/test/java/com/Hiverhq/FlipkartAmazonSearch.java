package com.Hiverhq;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Hiverhq.helpers.WaitHelper;
import com.Hiverhq.utilities.BrowserFactory;
import com.Hiverhq.utilities.PropertyHandler;

public class FlipkartAmazonSearch {
	FlipkartiPhone objFlipkartiPhone;
	AmazoniPhone objAmazoniPhone;
	WebDriver driver;
	
	public static Logger log = com.Hiverhq.helpers.LoggerHelper.getLogger(FlipkartAmazonSearch.class);
	
	@Parameters({ "browser", "runmode" })
	@BeforeMethod
	public void beforeMethod(@Optional("Chrome") String browser, @Optional("Local") String runmode)  {
		driver = com.Hiverhq.utilities.BrowserFactory.getBrowser(runmode, browser);
	}
		
		@Test
		public void flipkartamazoncompare()
		{
			BrowserFactory.openurl(PropertyHandler.getPropertyHandlerInstance().getUrlFlipkart());
			objFlipkartiPhone = PageFactory.initElements(driver, FlipkartiPhone.class);
			objFlipkartiPhone.popupclose();
			objFlipkartiPhone.searchproduct(PropertyHandler.getPropertyHandlerInstance().getProductName());
			String flipkartprice = objFlipkartiPhone.getprice().replaceAll("\\W+","");
			int finalflipkartprice = Integer.parseInt(flipkartprice);
			log.info("Flipkart price is "+finalflipkartprice);
			BrowserFactory.openurl(PropertyHandler.getPropertyHandlerInstance().getUrlAmazon());
			objAmazoniPhone = PageFactory.initElements(driver, AmazoniPhone.class);
			objAmazoniPhone.searchproduct(PropertyHandler.getPropertyHandlerInstance().getProductName());
			String amazonprice = objAmazoniPhone.getprice().replaceAll("\\W+","");
			int finalamazonprice = Integer.parseInt(amazonprice);
			log.info("Amazon price is "+finalamazonprice);
			if(finalflipkartprice > finalamazonprice)
			{
				log.info("Amazon has lesser price than Flipkart and the price is "+ finalamazonprice);
			}
			else
			{
				log.info("Flipkart has lesser price than Amazon and the price is "+ finalflipkartprice);
			}

		}
	
		
		
		@AfterMethod
		public void close() {
			driver.quit();
		}
}
