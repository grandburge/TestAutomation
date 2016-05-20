package com.test.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.testng.annotations.Test;

public class ParseXml {
	private Document document;
	
	public void load(String configPath)
	{
		File configFile = new File(configPath);
		if(configFile.exists())
		{
			SAXReader xmlReader = new SAXReader();
			try {
				document = xmlReader.read(configFile);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else
		{
			System.out.println("config not exist: "+configPath);
		}
	}
	
	public Element getElementObject(String elementPath)
	{
		return  (Element) document.selectSingleNode(elementPath);
	}	
	
	public boolean isElementExist(String elementPath)
	{
		Element element = this.getElementObject(elementPath);
		if(element != null)
			return true;
		else
			return false;
	}
	
	
	public String getElementText(String elementPath)
	{
		if(this.isElementExist(elementPath))
			return this.getElementObject(elementPath).getTextTrim();
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Element> getElementObjects(String elementPath)
	{
		return document.selectNodes(elementPath);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String> getChildrenInfoByElement(Element element)
	{
		if(element!=null)
		{
			Map<String,String> map = new HashMap<String,String>();
			List<Element> children = element.elements();
			for (Element e : children)
			{
				map.put(e.getName(), e.getText());
			}
			return map;
		}else{
			return null;
		}
		
				
	}
	
	@Test
	public void test(){
		this.load("Config/config2.xml");
//		List<Element> elements = this.getElementObjects("//test1");
		List<Element> elements = this.getElementObjects("//sdfsdf");
		Map<String,String> map;
		map = this.getChildrenInfoByElement(null);
//		for (Element e : elements)
//		{
//			map = this.getChildrenInfoByElement(e);
//			for (String key : map.keySet())
//			{
//				System.out.println(key+":"+map.get(key));				
//			}
//			System.out.println("+++++++++++++");
//		}
		
//		Object[][] object = new Object[elements.size()][];
//		for(int i=0;i<elements.size();i++)
//		{
//			Object[] temp = new Object[]{this.getChildrenInfoByElement(elements.get(i))};
//			object[i] = temp;
//		}
//		Object o = object[0][1];
//		for (String i : ((Map<String,String>) o).keySet())
//		{
//			System.out.println(i+":"+((Map<String,String>) o).get(i));
//		}
		
//		Map<String,String> map = this.getChildrenInfoByElement(elements.get(0));
//		for(String k : map.keySet())
//		{
//			System.out.println(map.get(k));
//		}
		
	}
}
