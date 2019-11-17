package com.Hiverhq;

import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.Hiverhq.helpers.BrowserHelper;
import com.Hiverhq.helpers.WaitHelper;

public class FlipkartHome {
	WebDriver driver;
	public static Logger log = com.Hiverhq.helpers.LoggerHelper.getLogger(FlipkartHome.class);
	
	public FlipkartHome() {
	/* default constructor */}
	
	public FlipkartHome(WebDriver driver) {

		this.driver = driver;
	}
	
	@FindBy(xpath = ".//*[@class='_2AkmmA _29YdH8']")
    private WebElement closebtn;
	
	@FindBy(xpath = ".//span[contains(text(),'Electronics')]")
    private WebElement category;
	
	@FindBy(xpath = ".//*[@title='Redmi K20 | K20 Pro']")
    private WebElement product;
	
	@FindBy(xpath = ".//*[@class='_3wU53n']")
	List<WebElement> mobilelist;
	
	@FindBy(xpath = ".//*[@class='_1vC4OE _3qQ9m1']")
	private WebElement mobileprice;
	
	@FindBy(xpath = ".//*[@class='_2AkmmA _2Npkh4 _2MWPVK']")
	private WebElement addtocart;
	
	@FindBy(xpath = ".//*[@class='_3md1dr']/button[2]")
	private WebElement quantity;
	
	@FindBy(xpath = ".//*[@class='_2zH4zg']")
	private int quantityvalue;
	
	@FindBy(xpath = ".//*[@class='_3xFQAD']/div/span")
	private WebElement price;
	
	public void popupclose()
	{
		closebtn.click();
	}
	
	public void hoveronproduct()
	{
		Actions action = new Actions(driver);
		action.moveToElement(category).perform();
	}
	
	public void clickonmobile()
	{
		WaitHelper.waitForElement(driver, product);
		product.click();
	}
	
	public void clickonfirstmobile()
	{
		List<WebElement> element = mobilelist;
		int i = 0;
			if(i == 0)
			{
				element.get(i).click();//clicking on 1st mobile
			}	
			List<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs.get(1));
	}
	
	public void clickonaddtocart()
	{
		addtocart.click();
	}
	
	public void increasequantity()
	{
		quantity.click();
		WaitHelper.waitForElement(driver, quantity);
	}
	
	public String getfinalprice()
	{
		return price.getText();
	}

  }

