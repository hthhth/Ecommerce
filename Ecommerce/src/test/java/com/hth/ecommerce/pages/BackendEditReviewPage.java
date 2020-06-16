package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BackendEditReviewPage {
	private WebDriver driver;
	
	@FindBy(how=How.ID, using="status_id")
	@CacheLookup
	private WebElement dropdownStatus;
	
	@FindBy(how=How.XPATH, using="//*[@id='save_button']")
	@CacheLookup
	private WebElement buttonSaveReview;
	
	public BackendEditReviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void chooseStatusOfApproved() {
		Select select = new Select(dropdownStatus);
		select.selectByVisibleText("Approved");
	}
	
	public void clickSaveReview() {
		buttonSaveReview.click();
	}
}
