package com.holin.steps;

import com.holin.pages.CartPage;
import com.holin.pages.MainPage;
import com.holin.pages.ResultPage;
import com.holin.util.DriverManager;
import com.holin.util.AttachmentsCreator;
import com.holin.util.TestProperties;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;


public class PageSteps {
    MainPage mainPage = new MainPage();
    ResultPage resultPage = new ResultPage();
    CartPage cartPage = new CartPage();
    WebDriver driver;


    @When("Поиск \"(.*)\"")
    public void findByName(String string) {
        driver = DriverManager.getDriver();
        mainPage = new MainPage();
        mainPage.search(string);
    }

    @When("Ограничиваем цену до \"(.*)\"")
    public void setMaxPrice(String name) {
        resultPage.setMaxPrice(name);
    }

    @When("Выбрать чекбокс \"(.*)\"")
    public void clickCheckbox(String name) {
        resultPage.chooseCheckbox(name);
    }

    @When ("Добавляем в корзину первые \"(.*)\" \"(.*)\" товаров")
    public void addItems (int count, String string) {
        resultPage.addItems(count, string);
    }

    @When("Переходим в корзину")
    public void goToBasket() {
        driver.get(DriverManager.properties.getProperty("app.basketurl"));
    }

    @When("Проверяем наличие товаров в соответствии с запомненным")
    public void assertItems() {
        cartPage.assertItems();
    }

    @When("Проверяем отображение текста Ваша корзина - \"(.*)\" товаров")
    public void checkItemsCount(int count) {
        cartPage.checkCount(count);
    }

    @When("Удаляем все товары из корзины")
    public void cleanBasket() {
        cartPage.cleanBasket();
    }

    @When("Проверяем, что \"(.*)\"")
    public void checkEmpty(String confirmText) {
        cartPage.checkEmpty(confirmText);
    }

    @When("Добавляем шаг, в котором будет приложен файл с информацией")
    public void addAttachment() {
        cartPage.attachmentStep();
    }
}
