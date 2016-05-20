package truview.page;

import org.openqa.selenium.WebDriver;

public class AdministrationSitesPage extends AdministrationPage {
	public AdministrationSitesPage(WebDriver driver)
	{
		super(driver);
	}
	
	public AdministrationSitesPage(WebDriver driver,String elementLocatorFile)
	{
		super(driver,elementLocatorFile);
	}
	
	public AdministrationSitesPage(WebDriver driver,String elementLocatorPath,String elementLocatorFile)
	{
		super(driver,elementLocatorPath,elementLocatorFile);	
	}
	
	public void addSite()
	{
		this.getElement("AddSiteBtn").click();
		
		this.getElement("SitePropertiesTab").click();
		this.getElement("SiteNameInput").sendKeys("RickySite");
		
		this.getElement("SubNetTab").click();
		this.getElement("AddSubnetBtn").click();
		this.getElement("IPAddressInput").sendKeys("1.1.1.1");
		this.getElement("SubnetMaskInput").sendKeys("32");
		this.getElement("SaveSubnetBtn").click();
		
		this.getElement("SaveSiteBtn").click();
	}
}
