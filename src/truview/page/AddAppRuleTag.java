package truview.page;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import truview.config.SeleniumDriver;

public class AddAppRuleTag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		// Add expression app	
		// Attribute: appName,	description, enabled, protocolType, appClassification
		// Operator: Equals, Does not equal, Contains, Does not contain, Starts with, Does not start with
		// appClassification:Business,Rogue,Other;		
		//protocolType:Web,Port Non-Streaming,Port Streaming,MySQL Database,MS SQL Database,Oracle Database,CIFS,Overwatch
		for(int h=17;h<=20;h++)
		{
			int initialNum = (h-1)*100;
			String tagName = "AppRuleTag"+h;
			HashMap<String[],String[][]> rules = new HashMap<String[],String[][]>();
			String[] ruleset1 = new String[]{"AppRuleTag_rule1","Any"};
			String[][] paras1 = new String[10][3];
			paras1[0]=new String[]{"appName","Equals","RickyAutoApp_"+initialNum};
			paras1[1]=new String[]{"description","Contains","test"};
			paras1[2]=new String[]{"enabled","Equals","true"};
			paras1[3]=new String[]{"protocolType","Equals","Web"};
			paras1[4]=new String[]{"appClassification","Equals","Business"};
			paras1[5]=new String[]{"appName","Does not equal","Ricky"};
			paras1[6]=new String[]{"description","Does not contain","smartbits"};
			paras1[7]=new String[]{"appName","Starts with","RickyAutoApp"};
			paras1[8]=new String[]{"description","Does not start with","RickyAutoApp"};
			paras1[9]=new String[]{"protocolType","Equals","Overwatch"};
			rules.put(ruleset1, paras1);
			for(int ruleIndex=2;ruleIndex<=10;ruleIndex++)
			{
				String[] ruleset = new String[]{"AppRuleTag_rule"+ruleIndex,"Any"};
				String[][] paras = new String[10][3];
				for(int j=0;j<paras.length;j++)
				{
					paras[j]=new String[]{"appName","Equals","RickyAutoApp_"+(initialNum+(ruleIndex-1)*10+j)};
				}
				rules.put(ruleset, paras);
			}

			administrationTagsPage.addRuleTag(tagName, "app manual tag for performance test", "Applications", rules);
			administrationTagsPage.wait(20000);
		}

	}

}
