package truview.page;

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
	
	public void addTag(String tagName,String description,String entity,String type,String value)
	{
		this.getElement("AddTagBtn").click();
		this.getElement("TagNameInput").sendKeys(tagName);
		this.getElement("DescriptionInput").sendKeys(description);
//		this.getElement("EntityTypeInput").click();
//		switch(entity)
//		{
//			case "Sites":
//				this.getElement("EntityTypeOptionSites").click();
////				this.getElement("EntityTypeInput").clear();
////				this.getElement("EntityTypeInput").sendKeys("Sites");
//				break;
//			case "Applications":
//				this.getElement("EntityTypeOptionApplications").click();
////				this.getElement("EntityTypeInput").clear();
////				this.getElement("EntityTypeInput").sendKeys("Applications");
//				break;
//			case "Servers":
//				this.getElement("EntityTypeOptionServers").click();
////				this.getElement("EntityTypeInput").clear();
////				this.getElement("EntityTypeInput").sendKeys("Servers");
//				break;
//			default:
//				System.out.println("The entity "+entity+" is invalid");
//				return;
//		}
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
			action .sendKeys(Keys.ENTER).perform();
//	The add tag value icon can't click in selenium
//			this.getElement("TagValueAddIcon").click();
		}
		this.getElement("SaveTagBtn").click();
	}
}
