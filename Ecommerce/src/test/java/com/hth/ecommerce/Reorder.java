package com.hth.ecommerce;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.*;

public class Reorder {
	private WebDriver driver;
	private HomePage homePage;
	private LogInPage logInPage;
	private AccountDashboardPage accountDashboardPage;
	private ShoppingCartPage shoppingCartPage;
	private CheckoutPage checkoutPage;
	private SuccessPage successPage;
	
	@DataProvider(name="login")
	public Object[][] getDataLogin() throws IOException {
		return Utils.getData(1, "Login");
	}
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateHomePage(driver);
	}
	@Test(dataProvider="login")
	public void reorder(String emailAddress, String password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		homePage = new HomePage(driver);
		homePage.clickLinkAccount();
		homePage.clickLinkMyAccount();
		
		logInPage = new LogInPage(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		logInPage.fillEmailPasswordAndLogin(emailAddress, password);
		
		accountDashboardPage = new AccountDashboardPage(driver);
		accountDashboardPage.clickLinkReorderOfLastestOrder();
		shoppingCartPage = new ShoppingCartPage(driver);
		shoppingCartPage.updateQty("2");
		shoppingCartPage = new ShoppingCartPage(driver);
		
		Assert.assertEquals(shoppingCartPage.getValueGrandTotal(), shoppingCartPage.calculateGrandTotal());
		
		shoppingCartPage.clickProceedToCheckout();

		checkoutPage = new CheckoutPage(driver);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Flow Close Button']")));
		driver.findElement(By.xpath("//*[@title='Flow Close Button']")).click();
		
		checkoutPage.clickButtonContinueUnderBilling();
		
		
		wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.getButtonContinueUnderShippingMethod()));
		checkoutPage.clickButtonContinueUnderShippingMethod(driver);
		
		wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.getRadioButtonCheckMoneyOrder()));
		checkoutPage.clickRadioButtonCheckMoneyOrder();
		
		checkoutPage.clickButtonContinueUnderPaymentInfor();
		
		
		wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.getButtonPlaceOrder()));
		checkoutPage.clickButtonPlaceOrder();
		
		wait.until(ExpectedConditions.urlContains("success"));
		
		successPage = new SuccessPage(driver);
		String heading1 = successPage.getHeading1Text();
		String expectedHeading1 = "YOUR ORDER HAS BEEN RECEIVED.";
		String heading2 = successPage.getHeading2Text();
		String expectedHeading2 = "THANK YOU FOR YOUR PURCHASE!";
		
		try {
			Assert.assertEquals(heading1, expectedHeading1);
		} catch(Exception e) {
			System.out.println("Heading on success page is not YOUR ORDER HAS BEEN RECEIVED.");
			e.printStackTrace();
		}
		try {
			Assert.assertEquals(heading2, expectedHeading2);
		} catch(Exception e) {
			System.out.println("Heading on success page is not THANK YOU FOR YOUR PURCHASE!");
			e.printStackTrace();
		}	
		
		String expectedYourOrderNumberText = "Your order # is: ";
		try {
			Assert.assertTrue(driver.getPageSource().contains(expectedYourOrderNumberText));
		} catch(Exception e) {
			System.out.println("Text on success page is not \"Your order # is:\"");
			e.printStackTrace();
		}	
		
		String orderNumber = successPage.getOrderNumber();
		System.out.println(orderNumber);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
