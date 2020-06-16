package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver driver;
	
	@FindBy(how=How.LINK_TEXT, using="MOBILE")
	@CacheLookup
	private WebElement linkMobile; 
	
	@FindBy(how=How.LINK_TEXT, using="TV")
	@CacheLookup
	private WebElement linkTV; 
	
	@FindBy(how=How.XPATH, using="//*[text()='Account' and @class='label']")
	@CacheLookup
	private WebElement linkAccount; 
	
	@FindBy(how=How.LINK_TEXT, using="My Account")
	@CacheLookup
	private WebElement linkMyAccount; 
	
	@FindBy(how=How.XPATH, using="//*[@title='Advanced Search']")
	@CacheLookup
	private WebElement linkAdvancedSearch; 
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickLinkMobile() {
		linkMobile.click();
	}
	public void clickLinkTV() {
		linkTV.click();
	}
	public void clickLinkAccount() {
		linkAccount.click();
	}
	public void clickLinkMyAccount() {
		linkMyAccount.click();
	}
	public WebElement getLinkAccount() {
		return linkAccount;
	}
	public void clickLinkAdvancedSearch() {
		linkAdvancedSearch.click();
	}
}
