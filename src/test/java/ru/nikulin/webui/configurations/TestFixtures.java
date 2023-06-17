package ru.nikulin.webui.configurations;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class TestFixtures {
    @Autowired
    WebDriverPathConfigurator webDriverPathConfigurator;

    @Value("${full.page.screenshots}")
    private Boolean fullPageScreenshot;

    @Before(value = "@webui")
    public void setup() {
        webDriverPathConfigurator.setup();

        Configuration.downloadsFolder = "target/downloads";
        Configuration.reportsFolder = "";


        SelenideLogger.addListener("AllureSelenide", new AllureSelenideWithFullPageScreenshots()
                .fullPageScreenshots(fullPageScreenshot)
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
