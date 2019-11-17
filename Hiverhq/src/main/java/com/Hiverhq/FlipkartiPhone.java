package com.Hiverhq;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.Hiverhq.helpers.WaitHelper;

public class FlipkartiPhone {

	WebDriver driver;
	public static Logger log = com.Hiverhq.helpers.LoggerHelper.getLogger(FlipkartiPhone.class);
	
	public FlipkartiPhone() {
	/* default constructor */}
	
	public FlipkartiPhone(WebDriver driver) {

		this.driver = driver;
	}
	
	@FindBy(xpath = ".//*[@class='_2AkmmA _29YdH8']")
    private WebElement closebtn;
	
	@FindBy(xpath = ".//*[@class='LM6RPg']")
    private WebElement searchbox;
	
	@FindBy(xpath = ".//*[@class='_1vC4OE _2rQ-NK']")
    private WebElement price;
	
	public void popupclose()
	{
		closebtn.click();
	}
	
	public void searchproduct(String product)
	{
		Actions action = new Actions(driver);
		action.sendKeys(searchbox, product).sendKeys(Keys.ENTER).perform();
	}
	
	public String getprice()
	{
		return price.getText();
	}
}
