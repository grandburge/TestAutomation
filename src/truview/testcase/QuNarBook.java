package truview.testcase;

import java.util.Map;

import org.testng.annotations.Test;

import com.test.util.Log;

import truview.page.QuNarPage;

public class QuNarBook extends TestBase{
	@Test(dataProvider="providerMethod")
	public void testBookTicket(Map<String,String> para)
	{
		driver.get("http://flight.qunar.com/");
		QuNarPage qunarPage = new QuNarPage(driver);
		qunarPage.getElement("fromCity").clear();
		qunarPage.getElement("fromCity").sendKeys(para.get("fromcity"));
		qunarPage.getElement("toCity").clear();
		qunarPage.getElement("toCity").sendKeys(para.get("tocity"));
		qunarPage.getElement("datebox").clear();
		qunarPage.getElement("datebox").sendKeys(para.get("flydate"));
		qunarPage.getElement("searchButton").click();
		wait(1000);
		if(!qunarPage.isElementDisplayed("bookicon"))
		{
			screenShot.takeShot("ticket-search");
			Log.logError("Fail to search ticket");
		}
	}
}
