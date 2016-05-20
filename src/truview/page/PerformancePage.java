package truview.page;

import org.openqa.selenium.WebDriver;

public class PerformancePage extends BasePage {
	public PerformancePage(WebDriver driver)
	{
		super(driver);
	}
	
	public PerformancePage(WebDriver driver,String elementLocatorFile)
	{
		super(driver,elementLocatorFile);
	}
	
	public PerformancePage(WebDriver driver,String elementLocatorPath,String elementLocatorFile)
	{
		super(driver,elementLocatorPath,elementLocatorFile);	
	}
}
