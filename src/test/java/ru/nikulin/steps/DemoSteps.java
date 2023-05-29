package ru.nikulin.steps;

import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nikulin.constants.UserType;
import ru.nikulin.utils.ApiClient;

import java.io.ByteArrayInputStream;

@Component
public class DemoSteps {

    @Autowired
    ApiClient client;

    @When("step should pass")
    public void mockedGoodStep() {
        assert true;
    }

    @When("step should fails")
    public void mockedBadStep() {
        assert false;
    }

    @When("user opens different sites")
    public void selenideTest() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://spring.io/");
        addScreenshotToAllure(driver);
        driver.close();
    }

    @When("user send get request")
    public void apiTest() {
        Response response = RestAssured
                .given()
                .baseUri("https://www.ae.com")
                .basePath("/ugp-api")
                .header("aeLang", "en_US")
                .header("aeSite", "AEO_US")
                .header("x-access-token", client.getToken(UserType.GUEST, false))
                .get("/site/v1/allShipToCountries");

        response.then().statusCode(200);

    }

    public void addScreenshotToAllure(WebDriver driver) {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}
