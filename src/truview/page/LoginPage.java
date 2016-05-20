package truview.page;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	public LoginPage(WebDriver driver,String elementLocatorFile)
	{
		super(driver,elementLocatorFile);
	}
	
	public LoginPage(WebDriver driver,String elementLocatorPath,String elementLocatorFile)
	{
		super(driver,elementLocatorPath,elementLocatorFile);	
	}
	
	public MainPage login(String username,String password)
	{
		this.getElement("usernameInput").sendKeys(username);
		this.getElement("passwordInput").sendKeys(password);
		this.getElement("loginButton").click();
		return new MainPage(this.getDriver());
	}
}
