package com.hth.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BackendCustomerEditPage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"customer_info_tabs_account\"]")
	@CacheLookup
	private WebElement tabAccountInformation;
	
	@FindBy(how=How.XPATH, using="//*[@id='customer_info_tabs_account_content'][not(@style='display:none;')]//*[@id=\"_accountwebsite_id\"]")
	@CacheLookup
	private WebElement fieldAssociateToWebsite;
	
	@FindBy(how=How.XPATH, using="//*[@id='customer_info_tabs_account_content'][not(@style='display:none;')]//*[@id=\"_accountcreated_in\"]")
	@CacheLookup
	private WebElement fieldCreatedFrom;
	
	@FindBy(how=How.XPATH, using="//*[@id='customer_info_tabs_account_content'][not(@style='display:none;')]//*[@id=\"_accountnew_password\"]")
	@CacheLookup
	private WebElement fieldNewPassword;
	
	public BackendCustomerEditPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickTabAccountInformation() {
		tabAccountInformation.click();
	}

	public WebElement getFieldAssociateToWebsite() {
		return fieldAssociateToWebsite;
	}

	public WebElement getFieldCreatedFrom() {
		return fieldCreatedFrom;
	}

	public WebElement getFieldNewPassword() {
		return fieldNewPassword;
	}
	
}
