package truview.testcase;

import java.util.Map;

import org.testng.annotations.Test;

public class LoginTest extends TestBase {
	@Test(dataProvider="providerMethod")
	public void testLogin(Map<String,String> testDates)
	{
		System.out.println(testDates.get("username"));
		System.out.println(testDates.get("password"));
		System.out.println(testDates.get("inputValue"));
		System.out.println(testDates.get("url"));
	}
}
