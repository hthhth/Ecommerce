package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[text()='Create an Account']")
	@CacheLookup
	private WebElement linkCreateAnAccount;
	
	
	@FindBy(how=How.ID, using="email")
	@CacheLookup
	private WebElement emailAddress;
	
	
	@FindBy(how=How.ID, using="pass")
	@CacheLookup
	private WebElement password;
	
	@FindBy(how=How.ID, using="send2")
	@CacheLookup
	private WebElement buttonLogin;
	
	public LogInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void clickCreateAnAccount() {
		linkCreateAnAccount.click();
	}
	
	public void fillEmailPasswordAndLogin(String emailAddress, String password) {
		this.emailAddress.sendKeys(emailAddress);
		this.password.sendKeys(password);
		buttonLogin.click();
	}
}
