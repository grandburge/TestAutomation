package com.test.util;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;


public class DemoTest extends TestBase {
	WebDriver driver;
	
	@BeforeClass
	public void setup()
	{
		SeleniumDriver seleniumDriver = new SeleniumDriver();
		this.driver=seleniumDriver.getDriver();

	}
	
	
	@Test(dataProvider="providerMethod")
	public void testInput(Map<String,String> testData)
	{
		driver.get("file:///D:/test/demo.html");
		Locator demoLocator = new Locator(driver,"demo");
		demoLocator.getElement("Input").sendKeys(testData.get("inputValue"));
	}

}
