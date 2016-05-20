package truview.page;

import org.openqa.selenium.WebDriver;

public class AdministrationPage extends BasePage{
	public AdministrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	public AdministrationPage(WebDriver driver,String elementLocatorFile)
	{
		super(driver,elementLocatorFile);
	}
	
	public AdministrationPage(WebDriver driver,String elementLocatorPath,String elementLocatorFile)
	{
		super(driver,elementLocatorPath,elementLocatorFile);	
	}
	
	public boolean isPageOpened()
	{
		if(this.isElementDisplayed("AdministrationLogo"))
			return true;
		else
			System.out.println("Administration is not displayed in AdministrationPage");
			return false;
	}
	
	public AdministrationSitesPage switchToSites()
	{
		if(!this.isElementDisplayed("SitesNavigation"))
			this.getElement("AnalysisNavigation");
		this.getElement("SitesNavigation").click();;
		return new AdministrationSitesPage(this.getDriver());
	}
}
