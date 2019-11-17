package com.Hiverhq;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AmazoniPhone {
	
	WebDriver driver;
	public static Logger log = com.Hiverhq.helpers.LoggerHelper.getLogger(AmazoniPhone.class);
	
	public AmazoniPhone() {
	/* default constructor */}
	
	public AmazoniPhone(WebDriver driver) {

		this.driver = driver;
	}
	
	@FindBy(id = "twotabsearchtextbox")
    private WebElement searchbox;
	
	@FindBy(id = "priceblock_dealprice")
    private WebElement price;
	
	@FindBy(xpath = ".//*[contains(text(),'Apple iPhone XR (64GB) - Yellow')]//ancestor::span[1]//*[contains(@class,'a-price-whole')]")
    private WebElement newprice;
	
	public void searchproduct(String product)
	{
		Actions action = new Actions(driver);
		action.sendKeys(searchbox, product).sendKeys(Keys.ENTER).perform();
	}
	
	public String getprice()
	{
		return newprice.getText();
	}
	

}
