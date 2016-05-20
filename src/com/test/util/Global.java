package com.test.util;

import java.util.Map;

public class Global {
	public static Map<String,String> global;
	
	static
	{
		ParseXml px = new ParseXml();
		px.load("TestData/Global.xml");
		global = px.getChildrenInfoByElement(px.getElementObject("/*"));
	}

}
