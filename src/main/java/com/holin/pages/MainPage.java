package com.holin.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BasePage {

	@FindBy(xpath = "//input [@placeholder='Искать на Ozon']")
	WebElement input;

	@FindBy(xpath = "//button [@type='submit']")
	WebElement submitButton;

	@Step("Поиск {text}")//TODO
	public void search(String text) {
		input.sendKeys(text);
		submitButton.click();
	}
}
