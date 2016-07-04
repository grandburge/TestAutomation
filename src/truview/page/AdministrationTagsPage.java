package truview.page;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class AdministrationTagsPage extends AdministrationPage {
	Actions action;
	public AdministrationTagsPage(WebDriver driver)
	{
		super(driver);
		this.action = new Actions(this.getDriver());
	}
	
	public AdministrationTagsPage(WebDriver driver,String elementLocatorFile)
	{
		super(driver,elementLocatorFile);
	}
	
	public AdministrationTagsPage(WebDriver driver,String elementLocatorPath,String elementLocatorFile)
	{
		super(driver,elementLocatorPath,elementLocatorFile);	
	}
	
	public void addManualTag(String tagName,String description,String entity,String value)
	//example:  addManualTag("tagname","for test","Sites","value1,value2")
	{
		this.getElement("AddTagBtn").click();
		this.getElement("TagNameInput").sendKeys(tagName);
		this.getElement("DescriptionInput").sendKeys(description);
		this.getElement("EntityTypeInput").click();
		switch(entity)
		{
			case "Sites":
				this.getElement("EntityTypeOptionSites").click();
//				this.getElement("EntityTypeInput").clear();
//				this.getElement("EntityTypeInput").sendKeys("Sites");
				break;
			case "Applications":
				this.getElement("EntityTypeOptionApplications").click();
//				this.getElement("EntityTypeInput").clear();
//				this.getElement("EntityTypeInput").sendKeys("Applications");
				break;
			case "Servers":
				this.getElement("EntityTypeOptionServers").click();
//				this.getElement("EntityTypeInput").clear();
//				this.getElement("EntityTypeInput").sendKeys("Servers");
				break;
			default:
				System.out.println("The entity "+entity+" is invalid");
				return;
		}
////		this.getElement("DefinitionTypeSelect").click();
//		switch(type)
//		{
//			case "List":
////				this.getElement("DefinitionTypeOptionList").click();
//				this.getElement("DefinitionTypeInput").clear();
//				this.getElement("DefinitionTypeInput").sendKeys("List");
//				break;
//			case "Expression":
////				this.getElement("DefinitionTypeOptionExpression").click();
//				this.getElement("DefinitionTypeInput").clear();
//				this.getElement("DefinitionTypeInput").sendKeys("Expression");
//				break;
//			default:
//				System.out.println("The tag type "+type+" is invalid");
//				return;
//		}
		String[] tagVlaueList = value.split(",");
		for(String tagValue : tagVlaueList)
		{
			this.getElement("TagValueInput").sendKeys(tagValue);				
//			action .sendKeys(Keys.ENTER).perform();
//	The add tag value icon can't click in selenium
			this.getElement("TagValueAddIcon").click();
		}
		this.getElement("SaveTagBtn").click();
	}
	
	public void addRuleTag(String tagName,String description,String entity,HashMap<String[],String[][]> rules)
	{
		this.getElement("AddTagBtn").click();
		this.getElement("TagNameInput").sendKeys(tagName);
		this.getElement("DescriptionInput").sendKeys(description);
		JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
		this.getElement("EntityTypeInput").click();
		switch(entity)
		{
			case "Sites":
				this.getElement("EntityTypeOptionSites").click();
//				this.getElement("EntityTypeInput").clear();
//				this.getElement("EntityTypeInput").sendKeys("Sites");
//				js.executeScript("arguments[0].value=arguments[1]", this.getElement("EntityTypeInput"),"Sites");
				break;
			case "Applications":
				this.getElement("EntityTypeOptionApplications").click();
//				this.getElement("EntityTypeInput").clear();
//				this.getElement("EntityTypeInput").sendKeys("Applications");
//				js.executeScript("arguments[0].value=arguments[1]", this.getElement("EntityTypeInput"),"Applications");
				break;
			case "Servers":
				this.getElement("EntityTypeOptionServers").click();
//				this.getElement("EntityTypeInput").clear();
//				this.getElement("EntityTypeInput").sendKeys("Servers");
//				js.executeScript("arguments[0].value=arguments[1]", this.getElement("EntityTypeInput"),"Servers");
				break;
			default:
				System.out.println("The entity "+entity+" is invalid");
				return;
		}

		this.getElement("AssignmentMethodRuleDiv").click();
		
		for(String[] ruleset : rules.keySet())
		{
			this.getElement("TagValueInput").click();
			this.getElement("TagValueInput").sendKeys(ruleset[0]);
			this.getElement("TagValueAddIcon").click();
			if (ruleset[1].equals("Any"))
			{
				this.getElement("FilterTypeAnyDiv").click();
			}
			String[][] filterParameters = rules.get(ruleset);
			for(int i=0;i<filterParameters.length;i++)
			{
				String[] parameter = filterParameters[i];
				this.getElement("AddExpressionFilterBtn").click();
//				this.getElement("FieldNameInput").click();
//				this.wait(100);
//				this.getElement("FieldNameDiv").click();
//				this.wait(100);
//				String[] fieldNameOption=new String[]{parameter[0]};
//				this.getElement("FieldNameOptionList", fieldNameOption).click();
				js.executeScript("arguments[0].value=arguments[1]", this.getElement("FieldNameInput"),parameter[0]);
				this.getElement("OperatorDiv").click();
				js.executeScript("arguments[0].value=arguments[1]", this.getElement("OperatorInput"),parameter[1]);
				this.getElement("ValueDiv").click();
				this.getElement("ValueInput").sendKeys(parameter[2]);
			}
//			this.wait(5000);
		}
		
		
//		this.getElement("FilterTypeDiv").click();
//		String[] filterTypeOption = new String[]{filterType};
//		this.getElement("FilterTypeOption",filterTypeOption).click();
		
		this.getElement("SaveTagBtn").click();
	}
}
