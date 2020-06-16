package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShareYourWishlistPage {
	private WebDriver driver;
	
	@FindBy(how=How.ID, using="email_address")
	@CacheLookup
	private WebElement textAreaEmail;
	
	@FindBy(how=How.ID, using="message")
	@CacheLookup
	private WebElement message;
	
	@FindBy(how=How.XPATH, using="//*[@title='Share Wishlist']")
	@CacheLookup
	private WebElement buttonShareWishlist;
	
	public ShareYourWishlistPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void fillDataAndShareWishlist(String emailAddress, String message) {
		textAreaEmail.sendKeys(emailAddress);
		this.message.sendKeys(message);
		buttonShareWishlist.click();
	}
}
