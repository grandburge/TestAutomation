package truview.page;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import truview.config.SeleniumDriver;

public class test {

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
		
		
//Add list site		
//		
//		for(int i=1;i<=20;i++)
//		{
//			String TagName = "RickyTag"+i;
//			String TagValue = TagName+"_1";
//			for(int j=2;j<=100;j++)
//			{
//				TagValue+=","+TagName+"_"+j;
//			}
//			administrationTagsPage.addListTag(TagName, "hello", "Sites", "List", TagValue);
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		
//		String[][] paras=new String[][]{{"description","Does not equal","rickysite1"},{"siteName","Equals","rickysite2"}};
//		administrationTagsPage.addExpressionTag("ExpSiteTest2", "hello", "Sites", "Expression", "Match One",paras);
// Add expression site	
// Attribute: siteName,	description, country, province, city, inSpeed, outSpeed
// Operator: Equals, Does not equal, Contains, Does not contain, Starts with, Does not start with
		
				
		
		
		for(int h=18;h<=20;h++)
		{
			int initialNum = (h-1)*100;
			String tagName = "RickyRuleTag"+h;
			HashMap<String[],String[][]> rules = new HashMap<String[],String[][]>();
			String[] ruleset1 = new String[]{"RickyRuleTag_rule1","Any"};
			String[][] paras1 = new String[10][3];
			paras1[0]=new String[]{"siteName","Equals","RickyAutoSite_"+initialNum};
			paras1[1]=new String[]{"description","Contains","test"};
			paras1[2]=new String[]{"country","Starts with","China"};
			paras1[3]=new String[]{"province","Does not contain","Beijing"};
			paras1[4]=new String[]{"city","Does not start with","Beijing"};
			paras1[5]=new String[]{"inSpeed","Does not equal","100000"};
			paras1[6]=new String[]{"outSpeed","Equals","100000"};
			paras1[7]=new String[]{"siteName","Contains","RickyAutoSite"};
			paras1[8]=new String[]{"description","Equals","RickyAutoSite_"+initialNum};
			paras1[9]=new String[]{"city","Equals","Shanghai"};
			rules.put(ruleset1, paras1);
			for(int ruleIndex=2;ruleIndex<=10;ruleIndex++)
			{
				String[] ruleset = new String[]{"RickyRuleTag_rule"+ruleIndex,"Any"};
				String[][] paras = new String[10][3];
				for(int j=0;j<paras.length;j++)
				{
					paras[j]=new String[]{"siteName","Equals","RickyAutoSite_"+(initialNum+(ruleIndex-1)*10+j)};
				}
				rules.put(ruleset, paras);
			}

			administrationTagsPage.addRuleTag(tagName, "site manual tag for performance test", "Sites", rules);
			administrationTagsPage.wait(20000);
		}
//		for(int i=0;i<1;i++)
//		{
//			String[][] paras = new String[100][3];
//			paras[0]=new String[]{"siteName","Equals","RickyAutoSite_"+i};
//			paras[1]=new String[]{"description","Contains","RickyAutoSite_"+i};
//			paras[2]=new String[]{"country","Starts with","China"};
//			paras[3]=new String[]{"province","Does not contain","Beijing"};
//			paras[4]=new String[]{"city","Does not start with","Beijing"};
//			paras[5]=new String[]{"inSpeed","Does not equal","100000"};
//			paras[6]=new String[]{"outSpeed","Equals","100000"};
//			for(int j=7;j<paras.length;j++)
//			{
//				paras[j]=new String[]{"description","Equals","RickyAutoSite_"+i+"_"+j};
//			}
//			administrationTagsPage.addExpressionTag("RickyExpSiteTag_"+i, "This is for tag performance test", "Sites","Match One",paras);
//		
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	
	}

}
