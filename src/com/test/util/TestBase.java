package com.test.util;

import org.testng.annotations.Test;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.testng.annotations.DataProvider;

public class TestBase {
	private ParseXml testDateParser;
	private Map<String,String> commonData;
	private void initialParser()
	{
		if(testDateParser==null)
		{
			String testDateFileName = this.getClass().getSimpleName();
			String testDateFilePath = "TestData"+File.separator+testDateFileName+".xml";
			testDateParser = new ParseXml();
			testDateParser.load(testDateFilePath);
		}			
	}
	
	private void getCommonData()
	{
		if(this.commonData==null)
		{
			Element commonElement = this.testDateParser.getElementObject("/*/common");
			this.commonData = this.testDateParser.getChildrenInfoByElement(commonElement);
		}
	}
	
	
	
	@DataProvider
	public Object[][] providerMethod(Method method)
	{
		this.initialParser();
		this.getCommonData();
		String methodName = method.getName();						
		List<Element> testRounds = testDateParser.getElementObjects("/*/"+methodName);
		
		Object[][] testDates = new Object[testRounds.size()][];
		for (int i=0;i<testRounds.size();i++)
		{
			Map<String,String> testDate = testDateParser.getChildrenInfoByElement(testRounds.get(i));
			Map<String,String> mergeCommon = this.getMergeMapData(testDate, this.commonData);
			Map<String,String> mergeGlobal = this.getMergeMapData(mergeCommon,Global.global);
			testDates[i] = new Object[]{mergeGlobal};
		}
		return testDates;		
	}
	
	private Map<String,String> getMergeMapData(Map<String,String> map1,Map<String,String> map2)
	{
		if(map2!=null)
		{
			Iterator<String> iter = map2.keySet().iterator();
			while(iter.hasNext())
			{	String key = iter.next();
				String value = map2.get(key);
				if(!map1.containsKey(key))
					map1.put(key, value);
			}
		}		
		return map1;
	}
	


}
