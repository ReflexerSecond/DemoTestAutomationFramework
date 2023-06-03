package ru.nikulin.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nikulin.constants.UserType;
import ru.nikulin.utils.AeoApiClient;
import ru.nikulin.utils.ScenarioContext;

import java.io.ByteArrayInputStream;

public class DemoSteps {

    @Autowired
    AeoApiClient apiClient;

    @Autowired
    ScenarioContext scenarioContext;

    @Then("step should pass")
    public void mockedGoodStep() {
        assert true;
    }

    @Then("step should fails")
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

    @Given("user getting {string} token")
    public void getToken(String user) {
        apiClient.saveToken(UserType.valueOf(user), true);
    }

    @When("user getting all countries to ship")
    public void getAllShipToContries() {
        apiClient.sendRequest(HttpMethod.GET, "/site/v1/allShipToCountries");
    }

    @Then("status code was {int}")
    public void checkStatusCodeForRememberedResponse(int expectedStatus) {
        Response response = scenarioContext.getVar("lastResponse");
        response.then().statusCode(expectedStatus);
    }

    public void addScreenshotToAllure(WebDriver driver) {
        //TODO move it
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}
