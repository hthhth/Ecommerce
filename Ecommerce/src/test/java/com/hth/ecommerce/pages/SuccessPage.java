package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage {
	private WebDriver driver;

	@FindBy(how=How.TAG_NAME, using="h1")
	@CacheLookup
	private WebElement heading1;
	
	@FindBy(how=How.TAG_NAME, using="h2")
	@CacheLookup
	private WebElement heading2;
	
	@FindBy(how=How.XPATH, using="//*[@id='top']/body/div[1]/div/div[2]/div/div/p[1]/a")
	@CacheLookup
	private WebElement orderNumber;

	@FindBy(how=How.XPATH, using="//*[text()='Account' and @class='label']")
	@CacheLookup
	private WebElement linkAccount; 
	
	@FindBy(how=How.LINK_TEXT, using="My Account")
	@CacheLookup
	private WebElement linkMyAccount; 
	
	public SuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getHeading1Text() {
		return heading1.getText();
	}
	
	public String getHeading2Text() {
		return heading2.getText();
	}
	
	public String getOrderNumber() {
		return orderNumber.getText();
	}
	
	public void clickLinkAccount() {
		linkAccount.click();
	}
	
	public void clickLinkMyAccount() {
		linkMyAccount.click();
	}
}
