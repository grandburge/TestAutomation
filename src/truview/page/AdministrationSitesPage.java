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
	
	// addSite("autosite1","1.1.1.1/32 1.1.1.2/32")
	public void addSite(String siteName,String subnet)
	{
		String ipAddress;
		String mask;
		
		this.getElement("AddSiteBtn").click();
		
		this.getElement("SitePropertiesTab").click();
		this.getElement("SiteNameInput").sendKeys(siteName);
		
		this.getElement("SubNetTab").click();
		this.wait(500);
		String[] subnetList = subnet.split(" ");		
		for(String ipMask : subnetList)
		{	String[] ipMaskPair = ipMask.split("/");
			ipAddress = ipMaskPair[0];
			mask = ipMaskPair[1];
			this.getElement("AddSubnetBtn").click();
			this.getElement("IPAddressInput").sendKeys(ipAddress);
			this.getElement("SubnetMaskInput").sendKeys(mask);
			this.getElement("SaveSubnetBtn").click();
		}

		
		this.getElement("SaveSiteBtn").click();
		this.wait(1000);
	}
}
