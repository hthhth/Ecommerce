package com.hth.ecommerce.pages;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCartPage {
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@title='Qty']")
	@CacheLookup
	private WebElement textBoxQty; 
	
	@FindBy(how=How.XPATH, using="//*[text()='Update']")
	@CacheLookup
	private WebElement buttonUpdate; 
			
	@FindBy(how=How.XPATH, using="//*[@class='error-msg']//span")
	@CacheLookup
	private WebElement errorMessage; 
	
	@FindBy(how=How.XPATH, using="//*[text()='Empty Cart']")
	@CacheLookup
	private WebElement buttonEmptyCart; 
	
	@FindBy(how=How.XPATH, using="//*[text()='Proceed to Checkout']")
	@CacheLookup
	private List<WebElement> buttonProceedToCheckout; 
	
	@FindBy(how=How.ID, using="country")
	@CacheLookup
	private WebElement dropdownCountry; 
	
	@FindBy(how=How.ID, using="region_id")
	@CacheLookup
	private WebElement dropdownStateProvince; 
	
	@FindBy(how=How.ID, using="postcode")
	@CacheLookup
	private WebElement ZIP; 
	
	@FindBy(how=How.XPATH, using="//*[text()='Estimate']")
	@CacheLookup
	private WebElement buttonEstimate; 
	
	@FindBy(how=How.ID, using="s_method_flatrate_flatrate")
	@CacheLookup
	private WebElement radioButtonFixedFlatRate; 
	
	@FindBy(how=How.XPATH, using="//*[@id='co-shipping-method-form']/dl/dd/ul/li/label/span")
	@CacheLookup
	private WebElement shippingFee;
	
	@FindBy(how=How.XPATH, using="//*[@id='shopping-cart-totals-table']/tbody/tr/td[2]/span")
	@CacheLookup
	private WebElement subtotal;
	
	@FindBy(how=How.XPATH, using="//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")
	@CacheLookup
	private WebElement grandTotal;
	
	@FindBy(how=How.XPATH, using="//*[text()='Update Total']")
	@CacheLookup		
	private WebElement buttonUpdateTotal; 
			
	@FindBy(how=How.XPATH, using="//*[@id='shopping-cart-table']/tbody/tr/td[3]/span/span")
	@CacheLookup		
	private List<WebElement> unitPrice; 
	
	@FindBy(how=How.XPATH, using="//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")
	@CacheLookup		
	private List<WebElement> qty; 
	
	@FindBy(how=How.ID, using="coupon_code")
	@CacheLookup
	private WebElement textboxDiscountCodes;
	
	@FindBy(how=How.XPATH, using="//*[@title='Apply']")
	@CacheLookup
	private WebElement buttonApply;
	
	@FindBy(how=How.XPATH, using="//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")
	@CacheLookup
	private WebElement discountAmount;
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void updateQty(String qty) {
		textBoxQty.click();
		textBoxQty.clear();
		textBoxQty.sendKeys(qty);
		buttonUpdate.click();
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}
	
	public void clickButtonEmptyCart() {
		buttonEmptyCart.click();
	}
	public void clickProceedToCheckout() {
		buttonProceedToCheckout.get(1).click();
	}
	public void fillCountryStateProvinceZipAndEstimate(String country, String stateProvince, String ZIP) {
		Select selectCountry = new Select(dropdownCountry);
		selectCountry.selectByVisibleText(country);
		
		Select selectStateProvince = new Select(dropdownStateProvince);
		selectStateProvince.selectByVisibleText(stateProvince);
		
		this.ZIP.sendKeys(ZIP);
		
		buttonEstimate.click();
		
	}
	public void clickRadioButtonFixedFlatRate() {
		radioButtonFixedFlatRate.click();
	}
	
	public double getShippingFee() {
		String shippingFeeString = shippingFee.getText();
		shippingFeeString = shippingFeeString.replace("$","").replace(",","");
		return Double.parseDouble(shippingFeeString);
	}
	
	public double getValueSubtotal() {
		String subtotalString = subtotal.getText();
		subtotalString = subtotalString.replace("$","").replace(",","");
		return Double.parseDouble(subtotalString);
	}
	
	public double getValueGrandTotal() {
		String grandTotalString = grandTotal.getText();
		grandTotalString = grandTotalString.replace("$","").replace(",","");
		return Double.parseDouble(grandTotalString);
	}
	
	public void clickButtonUpdateTotal() {
		buttonUpdateTotal.click();
	}
	
	//get unit price of all rows
	public List<Double> getUnitPrice() {
		List<String> priceString = new ArrayList<>();
		List<Double> priceDouble = new ArrayList<>();
		for (WebElement p : unitPrice) {
			priceString.add(p.getText());
		}
		for (String pString : priceString) {
			pString = pString.replace("$","").replace(",","");
			priceDouble.add(Double.parseDouble(pString));
		}
		return priceDouble;
	}
	
	// get qty of all rows
	public List<Integer> getQty() {
		List<String> qtyString = new ArrayList<>();
		List<Integer> qtyInteger = new ArrayList<>();
		for (WebElement q : qty) {
			qtyString.add(q.getAttribute("value"));
		}
		for (String qString : qtyString) {
			qtyInteger.add(Integer.parseInt(qString));
		}
		return qtyInteger;
	}
	
	public double calculateSubtotal() {
		double subtotal = 0.0;
		for (int i=0; i<getUnitPrice().size(); i++) {
			subtotal += getUnitPrice().get(i) * getQty().get(i);
		}
		return subtotal;
	}
	
	public double calculateGrandTotal() {
//		double grandTotal = 0.0;
//		double shippingFee = 0.0;
//		shippingFee = getShippingFee();
//		grandTotal += calculateSubtotal() + shippingFee;
		return calculateSubtotal();
	}
	
	public void applyCouponCode(String couponCode) {
		textboxDiscountCodes.clear();
		textboxDiscountCodes.sendKeys(couponCode);
		buttonApply.click();
	}
	
	public double getDiscountAmount() {
		return Double.parseDouble(discountAmount.getText().replace("-", "").replace("$", ""));
	}
}
