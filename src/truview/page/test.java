package truview.page;

import org.openqa.selenium.WebDriver;

import com.test.util.SeleniumDriver;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumDriver seleniumDriver = new SeleniumDriver();
		WebDriver driver = seleniumDriver.getDriver();
		driver.get("http://10.250.80.250/");
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.login("TVadmin", "tv");
		AdministrationPage administrationPage = mainPage.switchToAdmin();
//		AdministrationSitesPage administrationSitesPage = administrationPage.switchToSites();
//		administrationSitesPage.addSite("RickySite2","1.1.1.1/32 1.1.1.2/32");
		AdministrationTagsPage administrationTagsPage = administrationPage.switchToTags();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=19;i<=20;i++)
		{
			String TagName = "RickyTag"+i;
			String TagValue = TagName+"_1";
			for(int j=2;j<=100;j++)
			{
				TagValue+=","+TagName+"_"+j;
			}
			administrationTagsPage.addTag(TagName, "hello", "Sites", "List", TagValue);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
