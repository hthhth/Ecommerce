package com.hth.ecommerce;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.*;

public class InvoiceSortingByDate {
	private WebDriver driver;
	private BackendCustomersPage backendCustomersPage;
	private BackendInvoicesPage backendInvoicesPage;
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateBackEnd(driver);
		BackendLoginPage backendLoginPage = new BackendLoginPage(driver);
		backendLoginPage.loginBackEndSuccess(Utils.BACKEND_USER_NAME, Utils.BACKEND_PASSWORD);
	}
	
	@Test
	public void verifySortingWorksOnInvoiceDateColumn() throws InterruptedException {
		
		backendCustomersPage = new BackendCustomersPage(driver);
		backendCustomersPage.closePopup();
		backendCustomersPage.clickSalesInvoicesMenu(driver);
		Thread.sleep(1000);

		backendInvoicesPage = new BackendInvoicesPage(driver);
		List<LocalDateTime> listDate = backendInvoicesPage.getInvoiceDate();
		Collections.sort(listDate);
		List<LocalDateTime> listDateAscending = new ArrayList<>(listDate);
		Collections.reverse(listDate);
		List<LocalDateTime> listDateDescending = new ArrayList<>(listDate);;
		
		for (int i=0; i<2; i++) {
			backendInvoicesPage.clickHeaderInvoiceDate();
			Thread.sleep(2000);
			backendInvoicesPage = new BackendInvoicesPage(driver);
			String classNameOfHeaderInvoiceDate = backendInvoicesPage.getAttributeClassOfHeaderInvoiceDate();
			listDate = backendInvoicesPage.getInvoiceDate();
				
			if (classNameOfHeaderInvoiceDate.equals("sort-arrow-desc")) {
				Assert.assertEquals(listDate, listDateDescending);
			} else if (classNameOfHeaderInvoiceDate.equals("sort-arrow-asc")) {
				Assert.assertEquals(listDate, listDateAscending);
			}
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
