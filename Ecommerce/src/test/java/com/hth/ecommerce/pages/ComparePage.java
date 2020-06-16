package com.hth.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ComparePage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@id='top']/body/div/div[1]/h1")
	@CacheLookup
	private WebElement heading; 
	
	@FindBy(how=How.XPATH, using="//*[@id='product_comparison']/tbody[1]/tr[1]/td/a")
	@CacheLookup
	private List<WebElement> productLinks; 
	
	@FindBy(how=How.XPATH, using="//*[text()='Close Window']")
	@CacheLookup
	private WebElement buttonCloseWindow; 
	
	public ComparePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getHeading() {
		return heading.getText();
	}
	
	public int getCountOfProducts() {
		return productLinks.size();
	}
	
	public void clickButtonCloseWindow() {
		buttonCloseWindow.click();
	}
	
}
