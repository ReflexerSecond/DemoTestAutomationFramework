package ru.nikulin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoTestFrameworkApplication {

    @Value("${webdriver.chrome.driver}")
    private static String driverPath;


    public static void main(String[] args) {
        SpringApplication.run(DemoTestFrameworkApplication.class, args);
    }

}
