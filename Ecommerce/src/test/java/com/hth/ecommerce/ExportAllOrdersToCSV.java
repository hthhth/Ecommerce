package com.hth.ecommerce;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hth.ecommerce.pages.BackendCustomersPage;
import com.hth.ecommerce.pages.BackendLoginPage;

public class ExportAllOrdersToCSV {
	private WebDriver driver;
	private BackendCustomersPage backendCustomersPage;
	
	@BeforeTest
	public void setUp() {
		driver = Utils.navigateBackEnd(driver);
		BackendLoginPage backendLoginPage = new BackendLoginPage(driver);
		backendLoginPage.loginBackEndSuccess(Utils.BACKEND_USER_NAME, Utils.BACKEND_PASSWORD);
	}
	
	@Test
	public void exportAllOrders() throws InterruptedException, IOException {
		backendCustomersPage = new BackendCustomersPage(driver);
		backendCustomersPage.closePopup();
		backendCustomersPage.clickSalesOrdersMenu(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sales_order_grid_export")));
		backendCustomersPage.exportToCSV();

		Thread.sleep(15000);
		
		String userHome = System.getProperty("user.home");
		Path downloadPath = Paths.get(userHome + "/Downloads/orders.csv");
		List<String> lines = Files.readAllLines(downloadPath);
		for (String line : lines) {
			System.out.println(line);
		}
		
		EmailUtil.sendFileToEmail("", "", "", userHome + "/Downloads/orders.csv");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
