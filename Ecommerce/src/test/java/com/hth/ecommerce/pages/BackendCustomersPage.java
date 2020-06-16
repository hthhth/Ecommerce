package com.hth.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import sun.reflect.CallerSensitive;

public class BackendCustomersPage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@id='message-popup-window']/div[1]/a/span")
	@CacheLookup
	private WebElement buttonClosePopup;
	
	@FindBy(how=How.XPATH, using="//*[@id='nav']/li[1]/a/span")
	@CacheLookup
	private WebElement menuSales;
	
	@FindBy(how=How.XPATH, using="//*[@id='nav'/li[1]/ul/li[1]/a/span")
	@CacheLookup
	private WebElement linkOrders;
	
	@FindBy(how=How.XPATH, using="//*[@id='nav']/li[1]/ul/li[2]/a/span")
	@CacheLookup
	private WebElement linkInvoices;
	
	@FindBy(how=How.XPATH, using="//*[@id='nav']/li[2]/a/span")
	@CacheLookup
	private WebElement menuCatalog;
	
	@FindBy(how=How.XPATH, using="//*[@id='nav']/li[2]/ul/li/a/span")
	@CacheLookup
	private WebElement linkReviewsAndRatings;
	
	@FindBy(how=How.XPATH, using="//*[@id='nav']/li[2]/ul/li/ul/li[1]/a/span")
	@CacheLookup
	private WebElement linkCustomerReviews;
	
	@FindBy(how=How.XPATH, using="//*[@id='nav']/li[2]/ul/li/ul/li[1]/ul/li[1]/a/span")
	@CacheLookup
	private WebElement linkPendingReviews;
	
	@FindBy(how=How.ID, using="sales_order_grid_export")
	@CacheLookup
	private WebElement dropdownExportTo;
	
	@FindBy(how=How.XPATH, using="//*[@title='Export']")
	@CacheLookup
	private WebElement buttonExport;
	
	@FindBy(how=How.XPATH, using="//*[@id='customerGrid_table']/tbody/tr[1]/td[11]/a")
	@CacheLookup
	private WebElement linkEditOfFirstItem;
	
	public BackendCustomersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void closePopup() {
		buttonClosePopup.click();
	}
	
	public void clickSalesOrdersMenu(WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='nav']/li[1]/a/span"))).moveToElement(driver.findElement(By.xpath("//*[@id='nav']/li[1]/ul/li[1]/a/span"))).click().build().perform();
	}
	
	public void clickSalesInvoicesMenu(WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='nav']/li[1]/a/span"))).moveToElement(driver.findElement(By.xpath("//*[@id='nav']/li[1]/ul/li[2]/a/span"))).click().build().perform();
	}
	
	public void exportToCSV() {
		Select select = new Select(dropdownExportTo);
		select.selectByVisibleText("CSV");
		buttonExport.click();
	}

	public WebElement getDropdownExportTo() {
		return dropdownExportTo;
	}
	
	public void clickCatalogReviewsAndRatingCustomerReviewsPendingReviewsMenu(WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='nav']/li[2]/a/span")))
		.moveToElement(driver.findElement(By.xpath("//*[@id='nav']/li[2]/ul/li/a/span")))
		.moveToElement(driver.findElement(By.xpath("//*[@id='nav']/li[2]/ul/li/ul/li[1]/a/span")))
		.moveToElement(driver.findElement(By.xpath("//*[@id='nav']/li[2]/ul/li/ul/li[1]/ul/li[1]/a/span")))
		.click().build().perform();
	}
	
	public void clickCustomersManageCustomersMenu(WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='nav']/li[3]/a/span")))
		.moveToElement(driver.findElement(By.xpath("//*[@id='nav']/li[3]/ul/li/a/span")));
	}
	
	public void clickLinkEditOfFirstItem() {
		linkEditOfFirstItem.click();
	}
}
