package com.holin.steps;

import com.holin.util.DriverManager;
import com.holin.util.TestProperties;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;
public class BaseSteps {



	@Before
	public void initDriver(){
		WebDriver driver = DriverManager.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(TestProperties.getInstance().getProperties().getProperty("app.url"));
	}
}
