package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BackendLoginPage {
	private WebDriver driver;
	
	@FindBy(how=How.ID, using="username")
	@CacheLookup
	private WebElement userName;
	
	@FindBy(how=How.ID, using="login")
	@CacheLookup
	private WebElement password;
	
	@FindBy(how=How.XPATH, using="//*[@title='Login']")
	@CacheLookup
	private WebElement buttonLogin;
	
	public BackendLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void loginBackEndSuccess(String userName, String password) {
		this.userName.clear();
		this.userName.sendKeys(userName);
		this.password.clear();
		this.password.sendKeys(password);
		buttonLogin.click();
	}
	
}	
