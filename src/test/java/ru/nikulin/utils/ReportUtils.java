package ru.nikulin.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ReportUtils {
    public static void addScreenshotToAllure(String screenshotName) {
        Allure.addAttachment(screenshotName, new ByteArrayInputStream(((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    public static void addFullPageScreenshotToAllure(String screenshotName) throws IOException, InterruptedException {
        //TODO find out how to replace it
        Map<ByteArrayInputStream, Integer> screenshots = new HashMap<>();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();
        long width = Long.parseLong(jsExecutor.executeScript("return window.innerWidth;").toString());
        long height = Long.parseLong(jsExecutor.executeScript("return window.innerHeight;").toString());
        long offsetOld = 0L;
        long offsetNew = 0L;

        jsExecutor.executeScript("return window.scrollTo(0, 0);");

        do {
            Thread.sleep(1000);
            screenshots.put(new ByteArrayInputStream(((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES)), (int) offsetNew);
            offsetOld = offsetNew;

            jsExecutor.executeScript("return window.scrollTo(0, " + (offsetOld + height) + ")");
            offsetNew = Long.parseLong(jsExecutor.executeScript("return window.pageYOffset").toString());
        } while (offsetNew > offsetOld);

        BufferedImage finalImage = new BufferedImage((int) width, (int) offsetNew + (int) height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = finalImage.createGraphics();

        for (var image : screenshots.keySet()) {
            graphics2D.drawImage(ImageIO.read(image), 0, screenshots.get(image), null);
        }

        graphics2D.dispose();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(finalImage, "png", outputStream);
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            Allure.addAttachment(screenshotName, inputStream);
        }
    }
}
