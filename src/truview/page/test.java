package truview.page;

import org.openqa.selenium.WebDriver;

import com.test.util.SeleniumDriver;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumDriver seleniumDriver = new SeleniumDriver();
		WebDriver driver = seleniumDriver.getDriver();
		driver.get("http://129.196.33.150/");
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.login("TVadmin", "tv");
		AdministrationPage administrationPage = mainPage.switchToAdmin();
		AdministrationSitesPage administrationSitePage = administrationPage.switchToSites();
		administrationSitePage.addSite();
	}

}
