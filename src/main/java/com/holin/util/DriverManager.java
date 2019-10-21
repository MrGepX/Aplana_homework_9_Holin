package com.holin.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class DriverManager {

	public static Properties properties = TestProperties.getInstance().getProperties();

	private static WebDriver driver;

	public static WebDriver getDriver() {

		switch (properties.getProperty("browser")) {
			case "chrome" : if (driver == null) {
				System.setProperty(
					properties.getProperty("chromeDriverName"),
					properties.getProperty("chromeDriverPath"));
					driver = new ChromeDriver();
				}
				break;
			case "firefox" : if (driver == null) {
				System.setProperty(
					properties.getProperty("firefoxDriverName"),
					properties.getProperty("firefoxDriverPath"));
					driver = new FirefoxDriver();
				}
				break;
		}
		return driver;
	}

	public static void close(){
		if (driver!=null){
			driver.quit();
			driver = null;
		}
	}
}
