package com.holin.pages;

import com.holin.util.AttachmentsCreator;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

public class CartPage extends BasePage {
    @FindBy (xpath = "//a [@class = 'title']//span")
    List<WebElement> itemsInCart;

    @FindBy (xpath = "//span [@class = 'total-middle-header-text']")
    WebElement itemsCounter;

    @FindBy (xpath = "//span [contains(text(), 'Удалить выбранные')]")
    WebElement deleteAll;

    @FindBy (xpath = "//button [@data-test-id='checkcart-confirm-modal-confirm-button']")
    WebElement deleteAllConfirm;

    @FindBy (xpath = "//div/h1")
    WebElement emptyCartText;

    @Step ("Сверяем элементы")
    public void assertItems() {
        for (Map.Entry<String, Integer> entry : basket.entrySet()) {
            System.out.println(entry.getKey());
            boolean inBasket = false;
            for (WebElement webElement: itemsInCart) {
                wait.until(ExpectedConditions.elementToBeClickable(webElement));
                if (webElement.getText().equalsIgnoreCase(entry.getKey())) {
                    inBasket = true;
                }
            }
        Assert.assertTrue("Модель " + entry.getKey(), inBasket);
        }
    }

    @Step ("Проверяем отображение текста Ваша корзина {count} товара(ов)")
    public void checkCount(int count) {
        Assert.assertTrue(count == formatString(itemsCounter.getText().substring(0, 5)));
    }



    @Step ("Проверяем, что {confirmText}")
    public void checkEmpty(String confirmText) {
        Assert.assertEquals(emptyCartText.getText(), confirmText);
    }

    @Step ("Очищаем корзину")
    public void cleanBasket() {
        waitAndClick(deleteAll);
        waitAndClick(deleteAllConfirm);

    }

    @Step("Формируем приложение к отчету")
    public void attachmentStep() {
        AttachmentsCreator.addInfoFile(driver);
        basket.clear();
    }
}
