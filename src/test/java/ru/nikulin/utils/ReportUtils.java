package ru.nikulin.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ReportUtils {
    public static void addScreenshotToAllure(String screenshotName) {
        Allure.addAttachment(screenshotName, new ByteArrayInputStream(((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES)));
    }
}
