package com.hth.ecommerce;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.HomePage;
import com.hth.ecommerce.pages.LogInPage;
import com.hth.ecommerce.pages.MobileListPage;
import com.hth.ecommerce.pages.ShoppingCartPage;



public class DiscountCoupon {
	private WebDriver driver;
	private HomePage homePage;
	private LogInPage logInPage;
	private MobileListPage mobileListPage;
	private ShoppingCartPage shoppingCartPage;
	
	@DataProvider(name="login")
	public Object[][] getDataLogin() throws IOException {
		return Utils.getData(1, "Login");
	}
	
	@DataProvider(name="discount_coupon")
	public Object[][] getDiscountCoupon() throws IOException {
		return Utils.getData(1, "DiscountCoupon");
	}
	
	@DataProvider(name="login_AND_discount_coupon")
	public Object[][] getCombinedData() throws IOException{
		Object[][] data1 = this.getDataLogin();
		Object[][] data2 = this.getDiscountCoupon();
		Object[][] dataCombined = new Object[data1.length * data2.length][];
		int k=0;
		for (Object[] o1 : data1) {
			for (Object[] o2 : data2) {
					dataCombined[k] = ArrayUtils.addAll(o1, o2);
					k++;
			}
		}
		return dataCombined;
	}
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateHomePage(driver);
	}
	
	@Test(dataProvider="login_AND_discount_coupon")
	public void verifyDiscountCouponWorks(String emailAddress, String password, String discountCoupon) {
		homePage = new HomePage(driver);
		homePage.clickLinkAccount();
		homePage.clickLinkMyAccount();
		
		logInPage = new LogInPage(driver);
		logInPage.fillEmailPasswordAndLogin(emailAddress, password);
		
		homePage = new HomePage(driver);
		homePage.clickLinkMobile();
		
		mobileListPage = new MobileListPage(driver);
		mobileListPage.clickAddToCartAtIphone();
		
		shoppingCartPage = new ShoppingCartPage(driver);
		shoppingCartPage.applyCouponCode(discountCoupon);
		
		Assert.assertEquals(shoppingCartPage.getDiscountAmount(), shoppingCartPage.getValueSubtotal()*0.05);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
