package com.test.util;

public class RandomUntil {
	private static int getRandom(int count)
	{
		return (int) Math.round(Math.random()*count);
	}
	
	private static String rawString = "0123456789qwertyuiopasdfghjklzxcvbnm";
	
	public static String getRandomString(int len)
	{
		int count = rawString.length();
		StringBuffer stringBuffer = new StringBuffer();
		for(int i = 0;i<len;i++)
		{
			stringBuffer.append(rawString.charAt(getRandom(count-1)));
		}
		return stringBuffer.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(RandomUntil.getRandomString(8));
	}

}
