package com.test.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

public class Log {
	private static boolean flag = false;
	private static String configPath = "src/log4j.properties";
	private static Logger logger;
	
	private static synchronized void getPropertyFile()
	{
		logger = Logger.getLogger("Auto-Test");
		PropertyConfigurator.configure(configPath);		
		flag = true;
	}
	
	private static void getFlag()
	{
		if(Log.flag == false)
		{
			Log.getPropertyFile();
		}
	}
	
	public static void logInfo(String message)
	{
		Log.getFlag();
		logger.info(message);
		Reporter.log(TimeString.getSimpleDateFormat()+":"+message);
	}
	
	public static void logError(String message)
	{
		Log.getFlag();
		logger.error(message);
		Reporter.log(TimeString.getSimpleDateFormat()+":"+message);
	}
	
	public static void logWarn(String message)
	{
		Log.getFlag();
		logger.warn(message);
		Reporter.log(TimeString.getSimpleDateFormat()+":"+message);
	}	
}

