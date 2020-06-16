package com.hth.ecommerce;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.*;

public class ProductCostOnListPageAndDetailsPage {
	private WebDriver driver;
	private HomePage homePage;
	private MobileListPage mobileListPage;
	private MobileDetailsPage mobileDetailsPage;
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateHomePage(driver);
	}
	@Test
	public void verifyProductCostOnListPageAndDetailsPageEqual() throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.clickLinkMobile();
		Thread.sleep(1000);
		
		mobileListPage = new MobileListPage(driver);
		String costOfSonyXperiaOnMobileListPage = mobileListPage.getCostOfSonyXperia();

		mobileListPage.clickSonyXperia();
		Thread.sleep(1000);
		
		mobileDetailsPage = new MobileDetailsPage(driver);
		String costOfSonyXperiaOnDetailsPage = mobileDetailsPage.getCostOfProduct();

		Assert.assertEquals(costOfSonyXperiaOnDetailsPage, costOfSonyXperiaOnMobileListPage);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
