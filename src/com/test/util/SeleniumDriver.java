package com.test.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumDriver {
	private WebDriver driver;
	public SeleniumDriver()
	{
		this.initDriver();
	}
	private void initDriver()
	{
		if(Config.browser.equals("firefox"))
		{
			 ProfilesIni allProfiles=new ProfilesIni();
		     FirefoxProfile firefoxProfile = allProfiles.getProfile("default");
		     driver=new FirefoxDriver(firefoxProfile);
			
		}else if(Config.browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "browsedriver/chromedriver.exe");
			driver = new ChromeDriver();
		}else if(Config.browser.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", "browsedriver/iedriver.exe");
			driver = new InternetExplorerDriver();
		}
		
	}
	
	public WebDriver getDriver()
	{
		return this.driver;
	}

}
