package com.hth.ecommerce.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@title='Qty']")
	@CacheLookup
	private WebElement textBoxQty; 
	
	@FindBy(how=How.ID, using="billing:street1")
	@CacheLookup
	private WebElement billingAddress;
	
	@FindBy(how=How.ID, using="billing:city")
	@CacheLookup
	private WebElement billingCity;
	
	@FindBy(how=How.ID, using="billing:region_id")
	@CacheLookup
	private WebElement billingStateProvince;
	
	@FindBy(how=How.ID, using="billing:postcode")
	@CacheLookup
	private WebElement billingZip;
	
	@FindBy(how=How.ID, using="billing:country_id")
	@CacheLookup
	private WebElement billingCountry;
	
	@FindBy(how=How.ID, using="billing:telephone")
	@CacheLookup
	private WebElement billingTelephone;
	
	@FindBy(how=How.XPATH, using="//*[@id='billing-buttons-container']/button")
	@CacheLookup
	private WebElement buttonContinueUnderBilling;
	
	@FindBy(how=How.XPATH, using="//*[@id='shipping-method-buttons-container']/button")
	@CacheLookup
	private WebElement buttonContinueUnderShippingMethod;
	
	@FindBy(how=How.ID, using="p_method_checkmo")
	@CacheLookup
	private WebElement radioButtonCheckMoneyOrder;
	
	@FindBy(how=How.XPATH, using="//*[@id='payment-buttons-container']/button")
	@CacheLookup
	private WebElement buttonContinueUnderPaymentInfor;
	
	@FindBy(how=How.XPATH, using="//*[@title='Place Order']")
	@CacheLookup
	private WebElement buttonPlaceOrder;
	
	@FindBy(how=How.ID, using="billing-address-select")
	@CacheLookup
	private WebElement dropdownSelectBillingAddress;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void fillBillingInfor(String billingAddress, String billingCity, String billingStateProvince,
			String billingZip, String billingCountry, String billingTelephone) {
		this.billingAddress.clear();
		this.billingAddress.sendKeys(billingAddress);
		
		this.billingCity.clear();
		this.billingCity.sendKeys(billingCity);
		
		Select dropdownBillingStateProvince = new Select(this.billingStateProvince);
		dropdownBillingStateProvince.selectByVisibleText(billingStateProvince);
		
		this.billingZip.clear();
		this.billingZip.sendKeys(billingZip);
		
		Select dropdownCountry = new Select(this.billingCountry);
		dropdownCountry.selectByVisibleText(billingCountry);
		
		this.billingTelephone.clear();
		this.billingTelephone.sendKeys(billingTelephone);
	}
	
	public void clickButtonContinueUnderBilling() {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click();", buttonContinueUnderBilling);
	}
	
	public void clickButtonContinueUnderShippingMethod(WebDriver driver) {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//		javascriptExecutor.executeScript("arguments[0].click();", buttonContinueUnderShippingMethod);
		buttonContinueUnderShippingMethod.click();
	}
	
	public WebElement getRadioButtonCheckMoneyOrder() {
		return radioButtonCheckMoneyOrder;
	}
	
	public void clickRadioButtonCheckMoneyOrder() {
		radioButtonCheckMoneyOrder.click();
	}
	
	public void clickButtonContinueUnderPaymentInfor() {
		buttonContinueUnderPaymentInfor.click();
	}
	
	public void clickButtonPlaceOrder() {
		buttonPlaceOrder.click();
	}
	
	public void selectNewAddress() {
		Select dropdownSelectBillingAddress = new Select(this.dropdownSelectBillingAddress);
		dropdownSelectBillingAddress.selectByVisibleText("New Address");
	}

	public WebElement getButtonPlaceOrder() {
		return buttonPlaceOrder;
	}

	public WebElement getButtonContinueUnderShippingMethod() {
		return buttonContinueUnderShippingMethod;
	}
	
	
}
