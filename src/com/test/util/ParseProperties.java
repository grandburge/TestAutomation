package com.test.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.dom4j.Element;
import org.testng.annotations.Test;

public class ParseProperties {
	Properties prop;
	public void load(String configPath)
	{
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(configPath);
			prop.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
	}
	
	public String getElementText(String element)
	{
		return prop.getProperty(element);		
	}
	
	public boolean isElementExist(String element)
	{
		String value = prop.getProperty(element);
		if(value != null)
			return true;
		else
			return false;
	}
	
	@Test
	public void test()
	{
		this.load("Config/config.properties");
		System.out.println(this.getElementText("br"));
	}
}
