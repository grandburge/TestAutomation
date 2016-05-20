package truview.page;

import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{
	public MainPage(WebDriver driver)
	{
		super(driver);
	}
	
	public MainPage(WebDriver driver,String elementLocatorFile)
	{
		super(driver,elementLocatorFile);
	}
	
	public MainPage(WebDriver driver,String elementLocatorPath,String elementLocatorFile)
	{
		super(driver,elementLocatorPath,elementLocatorFile);	
	}
	
	public boolean isPageOpened()
	{
		if(this.isElementDisplayed("logo"))
			return true;
		else
			System.out.println("Logo is not displayed in MainPage");
			return false;
	}
	
	public AdministrationPage switchToAdmin()
	{
		this.getElement("AdministrationIcon").click();
		return new AdministrationPage(this.getDriver());
	}
	
	public PerformancePage switchToPerformance()
	{
		this.getElement("PerformanceIcon").click();
		return new PerformancePage(this.getDriver());
	}
	
}
