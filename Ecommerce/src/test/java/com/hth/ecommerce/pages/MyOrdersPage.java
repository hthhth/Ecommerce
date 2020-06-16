package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyOrdersPage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@id='my-orders-table']/tbody/tr[1]/td[1]")
	@CacheLookup
	private WebElement lastestOrderNumber; 
	
	@FindBy(how=How.XPATH, using="//*[@id='my-orders-table']/tbody/tr[1]/td[5]/em")
	@CacheLookup
	private WebElement lastestOrderStatus; 
	
	@FindBy(how=How.XPATH, using="//*[@id='my-orders-table']/tbody/tr[1]/td[6]/span/a[1]")
	@CacheLookup
	private WebElement linkViewOrderOfLastestOrder; 
	
	public MyOrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getLastestOrderNumber() {
		return lastestOrderNumber.getText();
	}

	public String getLastestOrderStatus() {
		return lastestOrderStatus.getText();
	}
	
	public void clickLinkViewOrderOfLastestOrder() {
		linkViewOrderOfLastestOrder.click();
	}
}
