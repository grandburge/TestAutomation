package com.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeString {

	
	public static String getSimpleDateFormat()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	
	 public static void main(String[] args){
		 System.out.println(TimeString.getSimpleDateFormat());
	 }
}
