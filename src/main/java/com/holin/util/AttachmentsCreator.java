package com.holin.util;

import com.holin.pages.BasePage;
import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AttachmentsCreator extends AllureJunit4 {
    @Override
    public void testFailure(Failure failure) {
        super.testFailure(failure);
        takeScreenshot(DriverManager.getDriver());
    }

    @Attachment(type = "image/png", value = "Screenshot")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(type = "text/txt", value = "AdditionalInfo")
    public static String addInfoFile(WebDriver driver) {
        StringBuilder data = new StringBuilder();
        data.append(BasePage.writeTheBasket());
        data.append("The most expensive phone = " + BasePage.findMostExpensivePhone());

        return data.toString();
    }
}
