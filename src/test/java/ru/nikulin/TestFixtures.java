package ru.nikulin;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.selenide.AllureSelenide;
import org.springframework.beans.factory.annotation.Value;

public class TestFixtures {
    @Value("${webdriver.chrome.driver}")
    private String driverPath;

    @Before(value = "@webui")
    public void setup() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        Configuration.downloadsFolder = "target/downloads";
        Configuration.reportsFolder = "";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(false)
        );
    }

    @After(value = "@webui")
    public void destroy() {
        Selenide.closeWebDriver();
    }
}
