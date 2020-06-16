package com.hth.ecommerce;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.BackendCustomerEditPage;
import com.hth.ecommerce.pages.BackendCustomersPage;
import com.hth.ecommerce.pages.BackendLoginPage;

public class DisabledFields {
	private WebDriver driver;
	private BackendCustomersPage backendCustomersPage;
	private BackendCustomerEditPage backendCustomerEditPage;
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateBackEnd(driver);
		BackendLoginPage backendLoginPage = new BackendLoginPage(driver);
		backendLoginPage.loginBackEndSuccess(Utils.BACKEND_USER_NAME, Utils.BACKEND_PASSWORD);
	}
	
	@Test
	public void verifyDisabledFields() {
		backendCustomersPage = new BackendCustomersPage(driver);
		backendCustomersPage.closePopup();
		backendCustomersPage.clickCustomersManageCustomersMenu(driver);
		backendCustomersPage.clickLinkEditOfFirstItem();
		
		backendCustomerEditPage = new BackendCustomerEditPage(driver);
		backendCustomerEditPage.clickTabAccountInformation();
		Assert.assertEquals(backendCustomerEditPage.getFieldAssociateToWebsite().getAttribute("disabled"), "true");
		Assert.assertEquals(backendCustomerEditPage.getFieldCreatedFrom().getAttribute("disabled"), "true");
		Assert.assertEquals(backendCustomerEditPage.getFieldNewPassword().getText(),"");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
}
