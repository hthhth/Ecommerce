package com.hth.ecommerce.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MobileListPage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select")
	@CacheLookup
	private WebElement dropdownSortBy; 
	
	@FindBy(how=How.XPATH, using="//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li/div/h2/a")
	@CacheLookup
	private List<WebElement> productsName; 
	
	@FindBy(how=How.XPATH, using="//*[@title='Sony Xperia']/parent::h2/following-sibling::div/span/span")
	@CacheLookup
	private WebElement costOfSonyXperia;
	
	@FindBy(how=How.XPATH, using="//*[@alt='Xperia']")
	@CacheLookup
	private WebElement mobileSonyXperia;
	
	@FindBy(how=How.XPATH, using="//*[@title='Sony Xperia']/parent::h2/following-sibling::div[3]/button")
	@CacheLookup
	private WebElement buttonAddToCartUnderSonyXperia;
	
	@FindBy(how=How.XPATH, using="//*[text()='Add to Wishlist']")
	@CacheLookup
	private List<WebElement> buttonsAddToWishlist;
	
	@FindBy(how=How.XPATH, using="//*[text()='Add to Compare']")
	@CacheLookup
	private List<WebElement> buttonsAddToCompare;
	
	@FindBy(how=How.XPATH, using="//*[text()='Compare']")
	@CacheLookup
	private WebElement buttonCompare;
	
	@FindBy(how=How.XPATH, using="//*[@title='IPhone']/following-sibling::div/div/button")
	@CacheLookup
	private WebElement buttonAddToCardAtIphone;
	
	public MobileListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getButtonsAddToWishlist() {
		return buttonsAddToWishlist;
	}
	
	public List<WebElement> getButtonsAddToCompare() {
		return buttonsAddToCompare;
	}

	public void selectSortBy(String option) {
		Select select = new Select(dropdownSortBy);
		select.selectByVisibleText(option);
	}
	
	public List<String> getProductsName(){
		List<String> names = new ArrayList<>();
		for (WebElement productName : productsName) {
			names.add(productName.getText());
		}
		return names;
	}
	
	public String getCostOfSonyXperia() {
		return costOfSonyXperia.getText();
	}
	
	public void clickSonyXperia() {
		mobileSonyXperia.click();
	}
	
	public void clickAddToCartUnderSonyXperia() {
		buttonAddToCartUnderSonyXperia.click();
	}
	
	public void clickAddToCompareButton(WebElement buttonAddToCompare) throws InterruptedException {
		buttonAddToCompare.click();
		Thread.sleep(1000);
	}
	
	public void clickAddToWishlistButton(WebElement buttonAddToWishlist) throws InterruptedException {
		buttonAddToWishlist.click();
		Thread.sleep(1000);
	}
	
	public void clickButtonCompare() {
		buttonCompare.click();
	}
	
	public void clickAddToCartAtIphone() {
		buttonAddToCardAtIphone.click();
	}
}
