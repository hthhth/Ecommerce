package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdvancedSearchPage {
	private WebDriver driver;
	
	@FindBy(how=How.ID, using="price")
	@CacheLookup
	private WebElement textboxPriceFrom; 
	
	@FindBy(how=How.ID, using="price_to")
	@CacheLookup
	private WebElement textboxPriceTo; 
	
	@FindBy(how=How.XPATH, using="//*[@title='Search'][@class='button']")
	@CacheLookup
	private WebElement buttonSearch; 
	
	public AdvancedSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchByPrice(String priceFrom, String priceTo) {
		textboxPriceFrom.clear();
		textboxPriceFrom.sendKeys(priceFrom);
		textboxPriceTo.clear();
		textboxPriceTo.sendKeys(priceTo);
		buttonSearch.click();
	}
}
