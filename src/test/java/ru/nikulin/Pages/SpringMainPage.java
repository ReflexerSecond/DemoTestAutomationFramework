package ru.nikulin.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.nikulin.constants.Constants;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Component
public class SpringMainPage {

    @Getter
    private final SelenideElement header = $(".header");
    @Getter
    private final SelenideElement centredText = $(".hero-springone-explore>*>.has-text-weight-medium");
    @Getter
    private final SelenideElement learnMoreButton = $(".springone-text>*>a.button.is-spring");
    @Getter
    private final ElementsCollection featureArticle = $$(".features > .feature");

    public static void open() {
        Selenide.open(Constants.SPRING_MAIN_PAGE_URL);
        new SpringMainPage();
    }

}