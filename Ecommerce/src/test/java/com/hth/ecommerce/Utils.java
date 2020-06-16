package com.hth.ecommerce;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.DataProvider;

import com.hth.ecommerce.pages.BackendLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utils {
	public static final String BASE_URL = "http://live.demoguru99.com/";
	public static final String BACKEND_URL = "http://live.demoguru99.com/index.php/backendlogin";
	public static final String BACKEND_USER_NAME = "user01";
	public static final String BACKEND_PASSWORD = "guru99com";
	
	
	public static WebDriver setUp() {
//		WebDriverManager.firefoxdriver().setup();
//		FirefoxOptions options = new FirefoxOptions();
//		options.addPreference("browser.download.folderList", 1);
//		options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream, application/pdf");
//		options.addPreference("pdfjs.disabled", true);
//		WebDriver driver = new FirefoxDriver(options);
//		return driver;

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public static WebDriver navigateHomePage(WebDriver driver) {
		driver = Utils.setUp();
		driver.manage().window().maximize();
		driver.get(BASE_URL);
		return driver;
	}
	
	public static WebDriver navigateBackEnd(WebDriver driver) {
		driver = Utils.setUp();
		driver.manage().window().maximize();
		driver.get(BACKEND_URL);
		return driver;
	}
	
	public static List<String> getSortedStringList(List<String> originalList){
		Collections.sort(originalList);
		return originalList;
	}
	
	public static Object[][] getData(int numberOfHeaderRows, String sheetName) throws IOException {
		String projectPath = System.getProperty("user.dir");
		ExcelUtils excel = new ExcelUtils(projectPath + "/data/Data.xlsx", sheetName);
		Object[][] data = new Object[excel.getRowCount()-numberOfHeaderRows][excel.getColCount()];
		for (int i=0; i<excel.getRowCount()-numberOfHeaderRows; i++) {
			for (int j=0; j<excel.getColCount(); j++) {
				data[i][j] = excel.getCellDataString(i+numberOfHeaderRows, j);
			}
		}
		return data;
	}
}
