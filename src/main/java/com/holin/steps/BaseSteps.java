package com.holin.steps;

import com.holin.util.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;
public class BaseSteps {

	@Before
	public void initDriver(){
		WebDriver driver = DriverManager.getDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(DriverManager.properties.getProperty("app.url"));
	}

	@After
	public void close() {
		DriverManager.close();
	}
}
