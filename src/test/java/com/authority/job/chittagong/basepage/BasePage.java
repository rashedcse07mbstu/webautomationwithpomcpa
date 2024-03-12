package com.authority.job.chittagong.basepage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public abstract class BasePage {
    public static WebDriver driver;
    public WebDriverWait wait;

    public abstract String getPageTittle();

    public void sendKeysElement(WebElement element, String text) throws InterruptedException {
        element.isDisplayed();
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        String source = ts.getScreenshotAs(OutputType.BASE64);
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(new File(source), finalDestination);
        return destination;
    }
}
