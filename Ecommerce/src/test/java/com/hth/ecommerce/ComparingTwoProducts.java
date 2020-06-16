package com.hth.ecommerce;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.ComparePage;
import com.hth.ecommerce.pages.HomePage;
import com.hth.ecommerce.pages.MobileListPage;

public class ComparingTwoProducts {
	private WebDriver driver;
	private HomePage homePage;
	private MobileListPage mobileListPage;
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateHomePage(driver);
	}
	@Test
	public void verifyCanCompareTwoProducts() throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.clickLinkMobile();
		Thread.sleep(1000);
		
		mobileListPage = new MobileListPage(driver);
		mobileListPage.clickAddToCompareButton(mobileListPage.getButtonsAddToCompare().get(0));
		mobileListPage = new MobileListPage(driver);
		mobileListPage.clickAddToCompareButton(mobileListPage.getButtonsAddToCompare().get(1));
		
		mobileListPage.clickButtonCompare();
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindow : allWindows) {
			if (!childWindow.equalsIgnoreCase(mainWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
				
		ComparePage comparePage = new ComparePage(driver);
		// Assert heading
		Thread.sleep(2000);
		String actualHeading = comparePage.getHeading();
		Thread.sleep(2000);
		String expectedHeading = "COMPARE PRODUCTS"; 
		Assert.assertEquals(actualHeading, expectedHeading);
		
		// Assert number of products
		int expectedProductsNumber = 2;
		int actualProductsNumber = comparePage.getCountOfProducts();
		Assert.assertEquals(actualProductsNumber, expectedProductsNumber);
		
		// Assert text "Description", "Short Description", "SKU"
		Assert.assertTrue(driver.getPageSource().contains("Description"));
		Assert.assertTrue(driver.getPageSource().contains("Short Description"));
		Assert.assertTrue(driver.getPageSource().contains("SKU"));
		
		comparePage.clickButtonCloseWindow();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
