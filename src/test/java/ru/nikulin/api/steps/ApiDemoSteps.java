package ru.nikulin.api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.openqa.selenium.remote.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nikulin.api.clients.AeoApiClient;
import ru.nikulin.api.constants.UserType;
import ru.nikulin.utils.ScenarioContext;

public class ApiDemoSteps {
    @Autowired
    AeoApiClient apiClient;
    @Autowired
    ScenarioContext scenarioContext;

    @Given("user getting {string} token")
    public void getToken(String user) {
        apiClient.saveToken(UserType.valueOf(user), true);
    }

    @When("user getting all countries to ship")
    public void getAllShipToCountries() {
        apiClient.sendRequest(HttpMethod.GET, "/site/v1/allShipToCountries");
    }

    @Then("status code was {int}")
    public void checkStatusCodeForRememberedResponse(int expectedStatus) {
        Response response = scenarioContext.getVar("lastResponse");
        response.then().statusCode(expectedStatus);
    }
}