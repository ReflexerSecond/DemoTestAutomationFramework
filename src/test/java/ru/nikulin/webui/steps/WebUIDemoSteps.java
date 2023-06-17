package ru.nikulin.webui.steps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nikulin.webui.pages.SpringMainPage;

public class WebUIDemoSteps {

    @Autowired
    SpringMainPage page;

    @Given("user opens spring main page")
    public void openMainPage() {
        SpringMainPage.open();
    }

    @Then("user checking elements on the spring.io main page")
    public void checkElements() {
        page.getHeader().shouldBe(Condition.visible);
        page.getCentredText().shouldBe(Condition.visible);
        page.getLearnMoreButton().shouldBe(Condition.visible);
        page.getFeatureArticle().forEach(x -> x.scrollTo().shouldBe(Condition.visible));
    }

    @When("user clicks on {int} feature")
    public void clickOnFeature(int featureNumber) {
        page.getFeatureArticle().get(featureNumber).scrollTo().shouldBe(Condition.visible).click();
    }

}
