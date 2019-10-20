package com.holin.pages;

import com.holin.util.AttachmentsCreator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultPage extends BasePage {

    public String resultCounter;

    @FindBy(xpath = "//input [@data-test-id= 'range-filter-to-input']")
    WebElement inputMaxPrice;

    @FindBy(xpath = "//span [@data-test-id= 'filter-block-brand-show-all']")
    WebElement showBrandList;

    @FindBy(xpath = "//* [contains(text(),'По запросу ')]")
    WebElement resultCounterElement;

    @Step ("Ограничиваем цену до {price}")
    public void setMaxPrice (String price) {
        while (!inputMaxPrice.getAttribute("value").equals("")) {
            inputMaxPrice.sendKeys(Keys.BACK_SPACE);
        }
        inputMaxPrice.sendKeys(price);
    }

    @Step ("Выбрать чекбокс {name}")
    public void chooseCheckbox(String name) {
        if (driver.findElements(By.xpath("//span [@data-test-id = 'filter-block-brand-show-all']")).size() != 0) {
            waitAndClick(driver.findElement(By.xpath("//span [@data-test-id = 'filter-block-brand-show-all']")));
        }

        if (driver.findElements(By.xpath("//div [@class = 'input-wrap search-input']/input")).size() != 0) {
            WebElement brandInput = driver.findElement(By.xpath("//div [@class = 'input-wrap search-input']/input"));
            waitAndClick(brandInput);
            brandInput.sendKeys(name);
        }

        setCurrentResultCount();
        List<WebElement> checkboxLabel = driver.findElements(By.xpath("//label [@class= 'checkbox-label']"));
        for (WebElement element: checkboxLabel) {
            if (element.getText().contains(name)) {
                waitAndClick(element);
                waitForChanges(resultCounterElement, resultCounter);
                return;
            }
        }
    }

    @Step ("Добавить в корзину {count} {string} элементов")
    public void addItems(int count, String string) {
        int mod = (string.contains("нечет")) ? 0 : 1;
        setCurrentResultCount();
        for (int i = mod; count > basket.size(); i++) {
            if (i >= Integer.parseInt(resultCounter)) {
                return;
            }
            List<WebElement> resultsList = driver.findElements(By.xpath("//div [@class= 'tile-wrapper']/div"));
            WebElement addButton = resultsList.get(i).findElement(By.xpath(".//button"));
            if (!resultsList.get(i).getText().contains("Узнать о поступлении") && i%2 == mod) {
                waitAndClick(addButton);
                basket.put(resultsList.get(i).findElement(By.xpath(".//a")).getText().trim(),
                        formatString(resultsList.get(i).findElement(By.xpath(".//span [@data-test-id = 'tile-price']")).getText().trim()));
            }
        }
    }

    public void setCurrentResultCount() {
        resultCounter = resultCounterElement.getText().replaceAll("[^0-9]", "");
    }
}
