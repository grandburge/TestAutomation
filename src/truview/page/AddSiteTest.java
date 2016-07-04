package truview.page;

import org.openqa.selenium.WebDriver;

import truview.config.SeleniumDriver;

public class AddSiteTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumDriver seleniumDriver = new SeleniumDriver();
		WebDriver driver = seleniumDriver.getDriver();
		driver.get("http://129.196.33.88/");
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.login("TVadmin", "tv");
		AdministrationPage administrationPage = mainPage.switchToAdmin();
		AdministrationSitesPage administrationSitesPage = administrationPage.switchToSites();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=970;i<971;i++)
		{
			int third = i/256;
			int forth = i%256;
			String subnet = "192.1."+third+"."+forth+"/32";
			administrationSitesPage.addSite("RickyAutoSite_"+i, subnet);
		}
		
		
	}

}
