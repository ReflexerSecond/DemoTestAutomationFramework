package ru.nikulin.webui.configurations;

import com.codeborne.selenide.logevents.LogEvent;
import io.qameta.allure.selenide.AllureSelenide;
import ru.nikulin.utils.ReportUtils;

import java.io.IOException;

public class AllureSelenideWithFullPageScreenshots extends AllureSelenide {

    private boolean savePageScreenshots = false;

    public AllureSelenideWithFullPageScreenshots() {
        super();
    }

    public AllureSelenide fullPageScreenshots(boolean savePageScreenshots) {
        this.savePageScreenshots = savePageScreenshots;
        return this;
    }

    @Override
    public void afterEvent(LogEvent event) {
        if (this.savePageScreenshots) {
            screenshots(false);
        }

        super.afterEvent(event);

        if (event.getStatus().equals(LogEvent.EventStatus.FAIL)) {
            try {
                if (this.savePageScreenshots) {
                    ReportUtils.addFullPageScreenshotToAllure("Screenshot");
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
