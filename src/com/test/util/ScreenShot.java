package com.test.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	public WebDriver driver;
	public String screenPath="D:/test/snapshot";
	File dir;
	
	public ScreenShot(WebDriver driver)
	{
		this.driver = driver;
		dir = new File(screenPath);
		if(!dir.exists())
		{
			dir.mkdirs();
		}
	}
	
	public void takeShot(String screenName)
	{
		Date date = new Date(); 
		SimpleDateFormat fmt = new SimpleDateFormat( "yyyy-MM-dd_HH_mm_ss");
		String name = String.valueOf(fmt.format(date))+"-"+screenName+".jpg";
		try
		{
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
			FileUtils.copyFile(screenshot, new File(this.dir.getAbsolutePath()+File.separator+name));	
		}catch(IOException e) {
			System.out.println("The path for screenshot is:"+dir.getAbsolutePath());
		}
		
	}
	
}