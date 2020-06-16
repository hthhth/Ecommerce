package com.hth.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyWishlistPage {
	private WebDriver driver;
	
	@FindBy(how=How.NAME, using="save_and_share")
	@CacheLookup
	private WebElement buttonShareWishlist;
	
	@FindBy(how=How.XPATH, using="//*[text()='Add to Cart']")
	@CacheLookup
	private List<WebElement> buttonsAddToCart;
	
	public MyWishlistPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickButtonShareWishlist() {
		buttonShareWishlist.click();
	}
	
	public void clickAButtonAddToCart() {
		buttonsAddToCart.get(0).click();
	}
}
