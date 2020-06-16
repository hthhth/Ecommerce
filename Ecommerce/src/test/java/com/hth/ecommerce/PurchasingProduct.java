package com.hth.ecommerce;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.*;

public class PurchasingProduct {
	private WebDriver driver;
	private HomePage homePage;
	private LogInPage logInPage;
	private AccountDashboardPage accountDashboardPage;
	private MyWishlistPage myWishlistPage;
	private ShoppingCartPage shoppingCartPage;
	private CheckoutPage checkoutPage;
	private SuccessPage successPage;
	private String orderNumber;
	private MyOrdersPage myOrdersPage;
	private OrderDetailsPage orderDetailsPage;
	
	@DataProvider(name="login")
	public Object[][] getDataLogin() throws IOException {
		return Utils.getData(1, "Login");
	}
	
	@DataProvider(name="estimate_shipping_tax")
	public Object[][] getDataEstimateShippingTax() throws IOException {
		return Utils.getData(1, "EstimateShippingAndTax");
	}
	
	@DataProvider(name="billing_infor")
	public Object[][] getDataBillingInfor() throws IOException {
		return Utils.getData(1, "BillingInfor");
	}
	
	@DataProvider(name="login_AND_estimate_shipping_tax_AND_billing_infor")
	public Object[][] getDataCombined() throws IOException {
		Object[][] data1 = this.getDataLogin();
		Object[][] data2 = this.getDataEstimateShippingTax();
		Object[][] data3 = this.getDataBillingInfor();
		Object[][] dataCombined = new Object[data1.length * data2.length * data3.length][];
		int k=0;
		for (Object[] o1 : data1) {
			for (Object[] o2 : data2) {
				for (Object[] o3 : data3) {
					dataCombined[k] = ArrayUtils.addAll(ArrayUtils.addAll(o1, o2), o3);
					k++;
				}	
			}
		}
		return dataCombined;
	}
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateHomePage(driver);
	}
	@Test(priority=1, dataProvider="login_AND_estimate_shipping_tax_AND_billing_infor")
	public void verifyUserPurchasesProductUsingRegisteredEmail(String emailAddress, String password,
			String country, String stateProvince, String ZIP, String billingAddress, String billingCity, 
			String billingStateProvince, String billingZip, String billingCountry, String billingTelephone) throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.clickLinkAccount();
		homePage.clickLinkMyAccount();
		
		logInPage = new LogInPage(driver);
		logInPage.fillEmailPasswordAndLogin(emailAddress, password);
		
		accountDashboardPage = new AccountDashboardPage(driver);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(accountDashboardPage.getLinkMyWishlist()));
		accountDashboardPage.clickLinkMyWishlist();
		
		myWishlistPage = new MyWishlistPage(driver);
		myWishlistPage.clickAButtonAddToCart();
		
		shoppingCartPage = new ShoppingCartPage(driver);
		shoppingCartPage.fillCountryStateProvinceZipAndEstimate(country, stateProvince, ZIP);
		try {	
			Assert.assertTrue(driver.getPageSource().contains("$5.00"));
		} catch (Exception e) {
			System.out.println("Shipping fee is not $5.00");
			e.printStackTrace();
		}
		shoppingCartPage.clickRadioButtonFixedFlatRate();
		shoppingCartPage.clickButtonUpdateTotal();
		
		double shippingFee = shoppingCartPage.getShippingFee();
		double subtotal = shoppingCartPage.getValueSubtotal();
		
		double grandTotalAfterShippingFee = shoppingCartPage.getValueGrandTotal();
		
		try {
			Assert.assertEquals(grandTotalAfterShippingFee, subtotal + shippingFee);
		} catch (Exception e) {
			System.out.println("Grand total is not equal to subtotal plus shipping fee");
			e.printStackTrace();
		}
		
		shoppingCartPage.clickProceedToCheckout();
		
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.selectNewAddress();
		checkoutPage.fillBillingInfor(billingAddress, billingCity, billingStateProvince, billingZip, billingCountry, billingTelephone);
		
		checkoutPage.clickButtonContinueUnderBilling();
		
		wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.getButtonContinueUnderShippingMethod()));
		checkoutPage.clickButtonContinueUnderShippingMethod(driver);
		
		wait.until(ExpectedConditions.visibilityOf(checkoutPage.getRadioButtonCheckMoneyOrder()));
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
		
		orderNumber = successPage.getOrderNumber();
		System.out.println(orderNumber);
	}
	@Test(priority=2, dataProvider="login")
	public void verifyCanSavePreviouslyPlacedOrderAsPDFFile(String emailAddress, String password) {
		successPage.clickLinkAccount();
		successPage.clickLinkMyAccount();
		
		accountDashboardPage = new AccountDashboardPage(driver);
		accountDashboardPage.clickLinkMyOrders();
		
		myOrdersPage = new MyOrdersPage(driver);
		try {
			Assert.assertEquals(myOrdersPage.getLastestOrderNumber(), orderNumber);
		} catch (Exception e) {
			System.out.println("Order number in page my order is not correct");
			e.printStackTrace();
		}
		try {
			Assert.assertEquals(myOrdersPage.getLastestOrderStatus(),"Pending");
		} catch (Exception e) {
			System.out.println("Order status in page my order is not correct");
			e.printStackTrace();
		}	
		
		myOrdersPage.clickLinkViewOrderOfLastestOrder();
		
		orderDetailsPage = new OrderDetailsPage(driver);
		String currentURL = driver.getCurrentUrl();
		String orderID = currentURL.substring(currentURL.length()-6, currentURL.length()-1);
		
		orderDetailsPage.clickLinkPrintOrder();
		String mainWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String childWindow : windows) {
			if (!childWindow.equalsIgnoreCase(mainWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
		try {
			Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/sales/order/print/order_id/" + orderID + "/");
		} catch(Exception e) {
			System.out.println("Not print to PDF");
			e.printStackTrace();
		}
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
