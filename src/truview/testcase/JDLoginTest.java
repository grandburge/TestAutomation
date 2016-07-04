package truview.testcase;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.util.Log;

import truview.page.JDFirstPage;
import truview.page.JDLoginPage;

public class JDLoginTest extends TestBase{
	@Test(dataProvider="providerMethod")
	public void testLogin(Map<String,String> para)
	{
		driver.get(url);
		JDFirstPage firstpage = new JDFirstPage(driver);
		firstpage.getElement("LoginLink").click();
		JDLoginPage loginpage = new JDLoginPage(driver);
		Log.logInfo("username and password is:"+para.get("username")+","+para.get("password"));
		loginpage.getElement("UsernameInput").sendKeys(para.get("username"));
		loginpage.getElement("PasswordInput").sendKeys(para.get("password"));
		loginpage.getElement("LoginSubmit").click();
		String errorMsg = loginpage.getElement("ErrorMessge").getText();
		Log.logInfo("Error Message is:"+errorMsg);
		if(!errorMsg.contains(para.get("warnmsg")))
		{
			Log.logError("µÇÂ¼Ê§°ÜÌáÊ¾ÐÅÏ¢´íÎó");
			screenShot.takeShot("testLogin");
			Assert.fail("µÇÂ¼Ê§°ÜÌáÊ¾ÐÅÏ¢´íÎó");
		}
	}

}
