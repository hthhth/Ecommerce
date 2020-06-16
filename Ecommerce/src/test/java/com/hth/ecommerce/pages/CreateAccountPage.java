package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {
	private WebDriver driver;
	
	@FindBy(how=How.ID, using="firstname")
	@CacheLookup
	private WebElement firstName;
	
	@FindBy(how=How.ID, using="middlename")
	@CacheLookup
	private WebElement middleName;
	
	@FindBy(how=How.ID, using="lastname")
	@CacheLookup
	private WebElement lastName;
	
	@FindBy(how=How.ID, using="email_address")
	@CacheLookup
	private WebElement emailAddress;
	
	@FindBy(how=How.ID, using="password")
	@CacheLookup
	private WebElement password;
	
	@FindBy(how=How.ID, using="confirmation")
	@CacheLookup
	private WebElement confirmPassword;
	
	@FindBy(how=How.XPATH, using="//span[text()='Register']")
	@CacheLookup
	private WebElement buttonRegister;
	
	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void fillDataAndRegister(String firstName, String middleName,
			String lastName, String emailAddress, String password, String confirmPassword) {
		this.firstName.sendKeys(firstName);
		this.middleName.sendKeys(middleName);
		this.lastName.sendKeys(lastName);
		this.emailAddress.sendKeys(emailAddress);
		this.password.sendKeys(password);
		this.confirmPassword.sendKeys(confirmPassword);
		buttonRegister.click();
	}
}
