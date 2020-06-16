package com.hth.ecommerce;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.AdvancedSearchPage;
import com.hth.ecommerce.pages.AdvancedSearchResultPage;
import com.hth.ecommerce.pages.HomePage;

public class SearchFunctionality {
	private WebDriver driver;
	private HomePage homePage;
	private AdvancedSearchPage advancedSearchPage;
	private AdvancedSearchResultPage advancedSearchResultPage;
	
	@DataProvider(name="advanced_search")
	public Object[][] getDataAdvancedSearch() throws IOException{
		return Utils.getData(1, "AdvancedSearch");
	}
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateHomePage(driver);
	}
	
	@Test(dataProvider="advanced_search")
	public void verifySearchFunctionality(String priceFrom, String priceTo) {
		homePage = new HomePage(driver);
		homePage.clickLinkAdvancedSearch();
		
		advancedSearchPage = new AdvancedSearchPage(driver);
		advancedSearchPage.searchByPrice(priceFrom, priceTo);
		
		advancedSearchResultPage = new AdvancedSearchResultPage(driver);
		for (int i=0; i<advancedSearchResultPage.getProductNames().size(); i++) {
			System.out.println(advancedSearchResultPage.getProductNames().get(i) + ": " 
					+ advancedSearchResultPage.getProductPrices().get(i));
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
