package truview.config;

import org.testng.annotations.Test;

import com.test.util.ParseXml;

public class Config {
	private static String configPath;
	public static String browser;
	public static int waitTime;
	public static String url;
	
	static
	{		
		configPath ="Config/config.xml";
		ParseXml XmlCfg = new ParseXml();
		XmlCfg.load(configPath);
		browser = XmlCfg.getElementText("/*/browser");
		waitTime = Integer.valueOf(XmlCfg.getElementText("/*/waitTime"));
		url = XmlCfg.getElementText("/*/url");
	}
	
	public void setCfgPath(String configPath)
	{
		Config.configPath = configPath;
	}
	
	public String getCfgParameter(String parameter)
	{
		ParseXml XmlCfg = new ParseXml();
		XmlCfg.load(Config.configPath);
		String elementPath = "/*/"+parameter;
		return XmlCfg.getElementText(elementPath);
	}
	
	@Test
	public void test(){
		Config cfg = new Config();
		cfg.setCfgPath("Config/config2.xml");
		System.out.println(cfg.getCfgParameter("browser"));
		System.out.println(Config.waitTime);
	}
}
