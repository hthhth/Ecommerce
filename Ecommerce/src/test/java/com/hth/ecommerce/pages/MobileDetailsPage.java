package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MobileDetailsPage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@id='product-price-1']/span")
	@CacheLookup
	private WebElement costOfProduct; 
	
	@FindBy(how=How.XPATH, using="//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/ul/li[2]/span")
	@CacheLookup
	private WebElement tabReviews;
			
	public MobileDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getCostOfProduct() {
		return costOfProduct.getText();
	}
	
	public void clickTabReview() {
		tabReviews.click();
	}
}
