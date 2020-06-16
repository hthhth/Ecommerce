package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BackendOrdersPage {
	private WebDriver driver;
	
	@FindBy(how=How.ID, using="sales_order_grid_filter_status")
	@CacheLookup
	private WebElement dropdownStatus;
	
	@FindBy(how=How.XPATH, using="//*[@title='Search']")
	@CacheLookup
	private WebElement buttonSearch;
	
	@FindBy(how=How.XPATH, using="//*[@id='sales_order_grid_table']/tbody/tr[1]/td[1]/input")
	@CacheLookup
	private WebElement checkboxOfFirstOrder;
	
	@FindBy(how=How.ID, using="sales_order_grid_massaction-select")
	@CacheLookup
	private WebElement dropdownActions;
	
	@FindBy(how=How.XPATH, using="//*[@title='Submit']")
	@CacheLookup
	private WebElement buttonSubmit;
	
	public BackendOrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void chooseStatusOfCanceled() {
		Select selectStatus = new Select(dropdownStatus);
		selectStatus.selectByVisibleText("Canceled");
	}
	
	public void chooseStatusOfComplete() {
		Select selectStatus = new Select(dropdownStatus);
		selectStatus.selectByVisibleText("Complete");
	}
	
	public void clickSearch() {
		buttonSearch.click();
	}
	
	public void clickCheckboxOfFirstOrder() {
		checkboxOfFirstOrder.click();
	}
	
	public void chooseActionOfPrintInvoices() {
		Select selectAction = new Select(dropdownActions);
		selectAction.selectByVisibleText("Print Invoices");
	}
	
	public void clickSubmit() {
		buttonSubmit.click();
	}
	
	public WebElement getDropdownStatus() {
		return dropdownStatus;
	}
	
	public WebElement getCheckboxOfFirstOrder() {
		return checkboxOfFirstOrder;
	}
}
