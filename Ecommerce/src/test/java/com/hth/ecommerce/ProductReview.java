package com.hth.ecommerce;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.*;

public class ProductReview {
	private WebDriver driver;
	private ReviewForm reviewForm;
	private BackendCustomersPage backendCustomersPage;
	private BackendEditReviewPage backendEditReviewPage;
	private HomePage homePage;
	private MobileListPage mobileListPage;
	private MobileDetailsPage mobileDetailsPage;
	
	@DataProvider(name="review")
	public Object[][] getDataOfReview() throws IOException{
		return Utils.getData(1, "ProductReview");
	}
	
	@BeforeTest
	public void setUp() {
		driver = Utils.setUp();
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="review")
	public void verifyProductReviewMechanism(String thoughts, String summary, String nickname) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("http://live.demoguru99.com/index.php/review/product/list/id/1/");
		reviewForm = new ReviewForm(driver);
		reviewForm.fillReviewAndSubmit(thoughts, summary, nickname);
		
		driver.get(Utils.BACKEND_URL);
		BackendLoginPage backendLoginPage = new BackendLoginPage(driver);
		backendLoginPage.loginBackEndSuccess(Utils.BACKEND_USER_NAME, Utils.BACKEND_PASSWORD);
		
		backendCustomersPage = new BackendCustomersPage(driver);
		backendCustomersPage.closePopup();
		backendCustomersPage.clickCatalogReviewsAndRatingCustomerReviewsPendingReviewsMenu(driver);
		
		String xpathLinkEdit = "//td[text()[contains(.,'" + nickname + "')]]/following-sibling::td[5]/a";
		WebElement linkEditOfTheSubmittedReview = driver.findElement(By.xpath(xpathLinkEdit));
		linkEditOfTheSubmittedReview.click();
		
		backendEditReviewPage = new BackendEditReviewPage(driver);
		backendEditReviewPage.chooseStatusOfApproved();
		backendEditReviewPage.clickSaveReview();
		
		driver.get(Utils.BASE_URL);
		homePage = new HomePage(driver);
		homePage.clickLinkMobile();
		
		mobileListPage = new MobileListPage(driver);
		mobileListPage.clickSonyXperia();
		
		mobileDetailsPage = new MobileDetailsPage(driver);
		mobileDetailsPage.clickTabReview();
		
		
		Assert.assertTrue(driver.getPageSource().contains(thoughts));
		Assert.assertTrue(driver.getPageSource().contains(summary));
		Assert.assertTrue(driver.getPageSource().contains("Review by " + nickname));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
