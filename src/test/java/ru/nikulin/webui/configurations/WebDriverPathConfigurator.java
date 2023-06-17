package ru.nikulin.webui.configurations;

import com.codeborne.selenide.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebDriverPathConfigurator {
    @Value("${webdriver.chrome.driver}")
    private String chromeDriverPath;

    @Value("${webdriver.gecko.driver}")
    private String firefoxDriverPath;

    @Value("${webdriver.ie.driver}")
    private String ieDriverPath;

    @Value("${selenide.browser}")
    private String selenideBrowser;

    public void setup() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
        System.setProperty("webdriver.ie.driver", ieDriverPath);
        Configuration.browser = selenideBrowser;
    }
}
