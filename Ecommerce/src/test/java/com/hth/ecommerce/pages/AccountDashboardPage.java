package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AccountDashboardPage {
	private WebDriver driver;
	
	@FindBy(how=How.LINK_TEXT, using="MY ORDERS")
	@CacheLookup
	private WebElement linkMyOrders;
	
	@FindBy(how=How.LINK_TEXT, using="MY WISHLIST")
	@CacheLookup
	private WebElement linkMyWishlist;
	
	@FindBy(how=How.XPATH, using="//*[@id='my-orders-table']/tbody/tr[1]/td[6]/span/a[2]")
	@CacheLookup
	private WebElement linkReorderOfLastestOrder; 
			
	public AccountDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickLinkMyWishlist() {
		linkMyWishlist.click();
	}

	public WebElement getLinkMyWishlist() {
		return linkMyWishlist;
	}
	
	public void clickLinkMyOrders() {
		linkMyOrders.click();
	}
	
	public void clickLinkReorderOfLastestOrder() {
		linkReorderOfLastestOrder.click();
	}
}
