package truview.page;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.ho.yaml.Yaml;
import org.openqa.selenium.WebDriver;

import truview.config.SeleniumDriver;

public class AddManualTag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumDriver seleniumDriver = new SeleniumDriver();
		WebDriver driver = seleniumDriver.getDriver();
		driver.get("http://129.196.33.88/");
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
		
//		administrationTagsPage.addManualTag("TagName", "hello", "Sites", "test1,test2");
//Add list site		
		
		for(int i=17;i<=20;i++)
		{
			String TagName = "AppManualTag"+i;
			String TagValue = TagName+"_app1";
			for(int j=2;j<=100;j++)
			{
				TagValue+=","+TagName+"_app"+j;
			}
			administrationTagsPage.addManualTag(TagName, "this is application manual tag for performance test", "Applications", TagValue);
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

	}

}
