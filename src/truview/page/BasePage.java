package truview.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.util.Config;

public class BasePage {
	private WebDriver driver;
	private Map<String,Map<String,String>> locatorMap;
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
		String filePath = "ElementLocator"+File.separator+this.getClass().getSimpleName()+".yaml";
		this.getYamlFile(filePath);		
	}
	
	public BasePage(WebDriver driver,String elementLocatorFile)
	{
		this.driver = driver;
		String filePath = "ElementLocator"+File.separator+elementLocatorFile+".yaml";
		this.getYamlFile(filePath);		
	}
	
	public BasePage(WebDriver driver,String elementLocatorPath,String elementLocatorFile)
	{
		this.driver = driver;
		String filePath = elementLocatorPath+File.separator+elementLocatorFile+".yaml";
		this.getYamlFile(filePath);		
	}
	
	private void getYamlFile(String filePath)
	{
//		File yamlFile = new File("ElementLocator"+File.separator+elementLocatorFile+".yaml");
		
		File yamlFile = new File(filePath);
		if(yamlFile.exists())
		{
			try {
				locatorMap = Yaml.loadType(new FileInputStream(yamlFile), HashMap.class);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			Assert.fail(filePath+"Not exist");
		}
		
	}
	
	private By getBy(String type,String value)
	{
		if(type != null && value != null)
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
			case "className":
				return By.className(value);
			default:
				return null;			
			}	
		}else
		{
			return null;
		}

	}
	
	private By getByFromKey(String key,String[] replace)
	{
		if(this.locatorMap.containsKey(key))
		{
			String type = this.locatorMap.get(key).get("type");
			String value = this.locatorMap.get(key).get("value");
			if(replace!=null)
				value = this.getLocatorString(value, replace);
			return this.getBy(type, value);
		}else
		{
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
	
	private boolean waitElementToBeDisplayed(final WebElement element)
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
//		String type = null;
//		String value = null;
		WebElement element = null;
		
//		if(locatorMap.containsKey(key))
//		{
//			type = locatorMap.get(key).get("type");
//			value = locatorMap.get(key).get("value");
//			if(replace != null)
//			{
//			value = this.getLocatorString(value, replace);
//			}
//			if(type==null | value==null)
//			{
//			return null;
//			}
//		}else
//		{
//			return null;
//		}
		
		By by = this.getByFromKey(key, replace);
		
		if(by==null)
		{
			Assert.fail("The element "+key+" doesn't not exist in the configuration file");
		}

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
//waitElementToBeDisplayed(element) doesn't work well sometimes,so bypass it.
				return this.waitForElement(by);
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
	
	public boolean isElementDisplayed(String key)
	{
		boolean displayFlag = false;
		if(key == null)
			displayFlag = false;
		else
		{
			By by = this.getByFromKey(key, null);
			WebElement element = this.waitForElement(by);
			displayFlag = this.waitElementToBeDisplayed(element);
		}
		return displayFlag;
	}
	
	protected WebDriver getDriver()
	{
		return this.driver;
	}


}


