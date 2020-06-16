package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class OrderDetailsPage {
	private WebDriver driver;
	
	@FindBy(how=How.LINK_TEXT, using="Print Order")
	@CacheLookup
	private WebElement linkPrintOrder;
	
	public OrderDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickLinkPrintOrder() {
		linkPrintOrder.click();
	}
}
