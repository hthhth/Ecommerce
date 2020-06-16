package com.hth.ecommerce;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.CreateAccountPage;
import com.hth.ecommerce.pages.HomePage;
import com.hth.ecommerce.pages.LogInPage;
import com.hth.ecommerce.pages.MobileListPage;
import com.hth.ecommerce.pages.MyWishlistPage;
import com.hth.ecommerce.pages.ShareYourWishlistPage;

public class AccountCreation {
	private WebDriver driver;
	private HomePage homePage;
	private LogInPage logInPage;
	private CreateAccountPage createAccountPage;
	private MobileListPage TVListPage;
	private MyWishlistPage myWishlistPage;
	private ShareYourWishlistPage shareYourWishlistPage;
	
	@DataProvider(name="create_account")
	public Object[][] getDataCreateAccount() throws IOException {
		return Utils.getData(1, "Account");
	}
	
	@DataProvider(name="share_wishlist")
	public Object[][] getDataShareWishlist() throws IOException {
		return Utils.getData(1, "ShareWishlist");
	}
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateHomePage(driver);
	}
	@Test(priority=1, dataProvider="create_account")
	public void verifyCanCreateAccount(String firstName, String middleName,
			String lastName, String emailAddress, String password, String confirmPassword) {
		homePage = new HomePage(driver);
		homePage.clickLinkAccount();
		homePage.clickLinkMyAccount();
		
		logInPage = new LogInPage(driver);
		logInPage.clickCreateAnAccount();
		
		createAccountPage = new CreateAccountPage(driver);
		createAccountPage.fillDataAndRegister(firstName, middleName, lastName, 
				emailAddress, password, confirmPassword);
		
		Assert.assertTrue(driver.getPageSource().contains("Thank you for registering with Main Website Store."));
	}
	@Test(priority=2)
	public void verifyAddingProductToWishlist() throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.clickLinkTV();
		
		TVListPage = new MobileListPage(driver);
		TVListPage.clickAddToWishlistButton(TVListPage.getButtonsAddToWishlist().get(0));
		
		Assert.assertTrue(driver.getPageSource().contains("has been added to your wishlist."));
	}
	
	@Test(priority=3, dataProvider="share_wishlist")
	public void verifyCanShareWishlistToOtherPeopleUsingEmail(String email, String message) {
		myWishlistPage = new MyWishlistPage(driver);
		myWishlistPage.clickButtonShareWishlist();
		
		shareYourWishlistPage = new ShareYourWishlistPage(driver);
		shareYourWishlistPage.fillDataAndShareWishlist(email, message);
		
		Assert.assertTrue(driver.getPageSource().contains("Your Wishlist has been shared."));
		System.out.println("Check inbox of email!!!");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
