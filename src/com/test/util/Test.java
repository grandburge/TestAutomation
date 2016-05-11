package com.test.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumDriver seleniumDriver = new SeleniumDriver();
		WebDriver driver=seleniumDriver.getDriver();
//		Locator baiduLocator = new Locator(driver,"TestBaidu");		
//		driver.get("http://www.baidu.com");
//		WebElement element = baiduLocator.getElement("baidu_input");
//		element.sendKeys("hello");
		
		driver.get("file:///D:/test/demo.html");
		Locator demoLocator = new Locator(driver,"demo");
		demoLocator.getElement("Input").sendKeys("hello");
		demoLocator.getElement("Link").click();
		

	}

}
