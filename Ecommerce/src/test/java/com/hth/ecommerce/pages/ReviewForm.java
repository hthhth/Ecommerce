package com.hth.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ReviewForm {
	private WebDriver driver;
	
	@FindBy(how=How.ID, using="review_field")
	@CacheLookup
	private WebElement thoughts;
			
	@FindBy(how=How.ID, using="summary_field")
	@CacheLookup
	private WebElement summary;
	
	@FindBy(how=How.ID, using="nickname_field")
	@CacheLookup
	private WebElement nickname;
	
	@FindBy(how=How.XPATH, using="//*[@title='Submit Review']")
	@CacheLookup
	private WebElement buttonSubmitReview;
	
	public ReviewForm(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void fillReviewAndSubmit(String thoughts, String summary, String nickname) {
		this.thoughts.clear();
		this.thoughts.sendKeys(thoughts);
		this.summary.clear();
		this.summary.sendKeys(summary);
		this.nickname.clear();
		this.nickname.sendKeys(nickname);
		buttonSubmitReview.click();
	}
	
}
