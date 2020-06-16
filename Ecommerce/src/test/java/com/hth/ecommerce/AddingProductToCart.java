package com.hth.ecommerce;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.HomePage;
import com.hth.ecommerce.pages.MobileListPage;
import com.hth.ecommerce.pages.ShoppingCartPage;

public class AddingProductToCart {
	private WebDriver driver;
	private HomePage homePage;
	private MobileListPage mobileListPage;
	private ShoppingCartPage shoppingCartPage;
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateHomePage(driver);
	}
	@Test
	public void verifyCanNotAddMoreProductToCartThanProductAvailableInStore() throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.clickLinkMobile();
		Thread.sleep(1000);
		
		mobileListPage = new MobileListPage(driver);
		mobileListPage.clickAddToCartUnderSonyXperia();
		
		shoppingCartPage = new ShoppingCartPage(driver);
		shoppingCartPage.updateQty("1000");
		
		String actualErrorMessage = shoppingCartPage.getErrorMessage();
		String expectedErrorMessage = "Some of the products cannot be ordered in requested quantity.";
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		
		shoppingCartPage.clickButtonEmptyCart();
		driver.getPageSource().contains("Shopping Cart is Empty");
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
