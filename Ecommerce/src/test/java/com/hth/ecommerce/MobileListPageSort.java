package com.hth.ecommerce;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.*;

public class MobileListPageSort {
	private WebDriver driver;
	private HomePage homePage;
	private MobileListPage mobileListPage;
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateHomePage(driver);
	}
	@Test
	public void verifyMobileListPageCanBeSortedByName() throws InterruptedException {
		
		homePage = new HomePage(driver);
		
		String currentPageTitle = driver.getTitle();
		String expectedPageTitle = "Home page";
		Assert.assertEquals(currentPageTitle, expectedPageTitle);
		
		Assert.assertTrue(driver.getPageSource().contains("This is demo site"));
		
		homePage.clickLinkMobile();
		Thread.sleep(1000);
		
		mobileListPage = new MobileListPage(driver);
		currentPageTitle = driver.getTitle();
		expectedPageTitle = "Mobile";
		Assert.assertEquals(currentPageTitle, expectedPageTitle);
		
		mobileListPage.selectSortBy("Name");
		List<String> productsName = mobileListPage.getProductsName();
		List<String> sortedProductsName = Utils.getSortedStringList(mobileListPage.getProductsName());
		Assert.assertEquals(productsName, sortedProductsName);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
