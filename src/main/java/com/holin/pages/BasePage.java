package com.holin.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import com.holin.util.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BasePage {

	WebDriver driver;
	public static Wait<WebDriver> wait;
	public static HashMap<String, Integer> basket = new HashMap<>();

	BasePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
		driver = DriverManager.getDriver();
		wait = new WebDriverWait(driver, 10, 2000);
	}

	public WebElement waitForElement(WebElement element){
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForChanges(WebElement element, String oldValue) {
		new WebDriverWait(driver, 5).until(new Function<WebDriver, Object>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return !element.getText().contains(oldValue);
			}
		});
	}

	public Integer formatString(String string) {
		return Integer.parseInt(string.replaceAll("[^0-9.,]", ""));
	}

	public void waitAndClick(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5, 200);
			wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
		} catch (WebDriverException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			webElement.click();
		}
	}


	public static String writeTheBasket() {
		StringBuilder data = new StringBuilder();
		for (Map.Entry<String, Integer> entry : basket.entrySet()) {
			data.append(entry.getKey()).append(" ").append(entry.getValue()).append('\n');
		}
		return data.toString();
	}

	public static String findMostExpensivePhone() {
		Map.Entry<String, Integer> maxEntry = null;

		for (Map.Entry<String, Integer> entry : basket.entrySet())
		{
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			{
				maxEntry = entry;
			}
		}
		return maxEntry.getKey() + " " + maxEntry.getValue();
	}
}
