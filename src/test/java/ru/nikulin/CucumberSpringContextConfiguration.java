package ru.nikulin;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CucumberContextConfiguration
public class CucumberSpringContextConfiguration {
    @Value("${webdriver.chrome.driver}")
    private String driverPath;

    @Before
    public void BeforeScenario() {
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

}
