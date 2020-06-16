package com.hth.ecommerce.pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BackendInvoicesPage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@name='created_at']")
	@CacheLookup
	private WebElement headerInvoiceDate;
	
	@FindBy(how=How.XPATH, using="//*[@id='sales_invoice_grid_table']/tbody/tr/td[3]")
	@CacheLookup
	private List<WebElement> invoiceDate;  
	
	public BackendInvoicesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickHeaderInvoiceDate() {
		headerInvoiceDate.click();
	}
	
	public String getAttributeClassOfHeaderInvoiceDate() {
		return headerInvoiceDate.getAttribute("class");
	}
	
	public List<LocalDateTime> getInvoiceDate() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:m:s a");
		List<LocalDateTime> listInvoiceDate = new ArrayList<>();
		for (WebElement date : invoiceDate) {
			listInvoiceDate.add(LocalDateTime.parse(date.getText(), dateTimeFormatter));
		}
		return listInvoiceDate;
	}

	public WebElement getHeaderInvoiceDate() {
		return headerInvoiceDate;
	}
	
	
}
