package com.hth.ecommerce;

import java.io.File;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.BackendCustomersPage;
import com.hth.ecommerce.pages.BackendLoginPage;
import com.hth.ecommerce.pages.BackendOrdersPage;

public class InvoicePrint {
	private WebDriver driver;
	private BackendCustomersPage backendCustomersPage;
	private BackendOrdersPage backendOrdersPage;
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateBackEnd(driver);
		BackendLoginPage backendLoginPage = new BackendLoginPage(driver);
		backendLoginPage.loginBackEndSuccess(Utils.BACKEND_USER_NAME, Utils.BACKEND_PASSWORD);
	}
	
	@Test
	public void verifyInvoiceCanBePrinted() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		backendCustomersPage = new BackendCustomersPage(driver);
		backendCustomersPage.closePopup();
		backendCustomersPage.clickSalesOrdersMenu(driver);
		
		backendOrdersPage = new BackendOrdersPage(driver);
		
		wait.until(ExpectedConditions.elementToBeClickable(backendOrdersPage.getDropdownStatus()));
		
		backendOrdersPage.chooseStatusOfCanceled();
		backendOrdersPage.clickSearch();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-mask")));
		
		backendOrdersPage.clickCheckboxOfFirstOrder();
		backendOrdersPage.chooseActionOfPrintInvoices();
		backendOrdersPage.clickSubmit();
		
		Assert.assertTrue(driver.getPageSource().contains("There are no printable documents related to selected orders."));
		
		backendOrdersPage = new BackendOrdersPage(driver);
		wait.until(ExpectedConditions.elementToBeClickable(backendOrdersPage.getDropdownStatus()));
		backendOrdersPage.chooseStatusOfComplete();
		backendOrdersPage.clickSearch();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-mask")));
		
		String userHome = System.getProperty("user.home");
		File filePath = new File(userHome + "/Downloads");
		File[] files = filePath.listFiles();
		int countDeletedFiles=0;
		for (File file : files) {
			if (file.getName().contains("invoice" + LocalDate.now())) {
				System.out.println("Deleted: " + file.getName());
				file.delete();
				countDeletedFiles++;
			}
		}	
		System.out.println("Deleted: " + countDeletedFiles + " files");	
		
		backendOrdersPage.clickCheckboxOfFirstOrder();
		backendOrdersPage.chooseActionOfPrintInvoices();
		backendOrdersPage.clickSubmit();
		Thread.sleep(5000);
		
		
		files = filePath.listFiles();
		boolean invoiceDownloaded = false;
		for (File f : files) {
			if (f.getName().contains("invoice" + LocalDate.now())) {
				invoiceDownloaded = true;
				break;
			} 
		}
		Assert.assertEquals(invoiceDownloaded, true);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
