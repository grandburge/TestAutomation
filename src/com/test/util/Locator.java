package com.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;










import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Locator {
	private WebDriver driver;
	private String elementLocatorFile;
	private Map<String,Map<String,String>> ml;
	
	public Locator(WebDriver driver,String elementLocatorFile)
	{
		this.driver=driver;
		this.elementLocatorFile=elementLocatorFile;
		this.getYamlFile();
		
	}
	
	private void getYamlFile()
	{
		File yamlFile = new File("ElementLocator"+File.separator+elementLocatorFile+".yaml");
		try {
			ml = Yaml.loadType(new FileInputStream(yamlFile), HashMap.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private By getBy(String type,String value)
	{
		switch(type)
		{
		case "id":
			return By.id(value);
		case "xpath":
			return By.xpath(value);
		case "name":
			return By.name(value);
		case "linkText":
			return By.linkText(value);
		default:
			return null;			
		}
	}
	
	private WebElement waitForElement(final By by)
	{
		WebElement element = null;
		try{
			 element = (new WebDriverWait(this.driver, Config.waitTime)).until(new ExpectedCondition<WebElement>(){
				  @Override
				  public WebElement apply(WebDriver d) {
				    return d.findElement(by);
				  }});
		}catch(Exception e){
			System.out.println("Warning: Element "+by.toString()+" not found until "+Config.waitTime);
			e.printStackTrace();
		}
		return element;
	}
	
	public boolean waitElementToBeDisplayed(final WebElement element)
	{
		boolean displayFlag = false;
		if(element == null)
			displayFlag = false;
		else
		{
			try{
				displayFlag = (new WebDriverWait(this.driver, Integer.parseInt(String.valueOf(Config.waitTime)))).until(new ExpectedCondition<Boolean>(){
					  @Override
					  public Boolean apply(WebDriver d) {
					    return element.isDisplayed();
					  }});
			}catch(Exception e){
				System.out.println("Warning: Element "+element.toString()+" not displayed until "+Config.waitTime);
				e.printStackTrace();
			}
		}
		return displayFlag;
	}
	
	private String getLocatorString(String value,String[] ss)
	{
		for(String s:ss)
		{
			value.replaceFirst("%s", s);
		}
		return value;
	}
	
	
	private WebElement getLocator(String key,String[] replace,boolean wait)
	{
		String type = null;
		String value = null;
		WebElement element = null;
		
		if(ml.containsKey(key))
		{
			type = ml.get(key).get("type");
			value = ml.get(key).get("value");
			if(replace != null)
			{
			value = this.getLocatorString(value, replace);
			}
			if(type==null | value==null)
			{
			return null;
			}
		}else
		{
			return null;
		}
		
		By by = this.getBy(type, value);

		if(wait == true)
		{
			element = this.waitForElement(by);
			if(this.waitElementToBeDisplayed(element))
			{
//				return element;
//Sometimes refreshing page may cause that the element is lost, so run waitForElement again to get the element
				return this.waitForElement(by);
			}
			else
			{
				return null;
			}
		}
		else
		{
			try
			{
			element = driver.findElement(by);
			}
			catch(Exception e)
			{
				element = null;
			}
			return element;
		}
	}
	
	public WebElement getElement(String key)
	{
		return this.getLocator(key, null, true);
	}
	
	public WebElement getElement(String key,String[] replace)
	{
		return this.getLocator(key, replace, true);
	}
	
	public WebElement getElementNoWait(String key)
	{
		return this.getLocator(key, null, false);
	}
	
	public WebElement getElementNoWait(String key,String[] replace)
	{
		return this.getLocator(key, replace, false);
	}
	


}
