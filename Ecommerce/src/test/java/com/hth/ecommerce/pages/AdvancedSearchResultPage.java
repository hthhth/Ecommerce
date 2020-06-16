package com.hth.ecommerce.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdvancedSearchResultPage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[3]/ul/li/div/h2/a")
	@CacheLookup
	private List<WebElement> productNames; 
	
	@FindBy(how=How.XPATH, using="//span[contains(@id,'product-price')]")
	@CacheLookup
	private List<WebElement> productPrices; 
	
	public AdvancedSearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<String> getProductNames(){
		List<String> productNameList = new ArrayList<>();
		for (WebElement name : productNames) {
			productNameList.add(name.getText());
		}
		return productNameList;
	}

	public List<String> getProductPrices(){
		List<String> productPriceList = new ArrayList<>();
		for (WebElement price : productPrices) {
			productPriceList.add(price.getText());
		}
		return productPriceList;
	}
}
