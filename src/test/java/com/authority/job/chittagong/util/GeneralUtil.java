package com.authority.job.chittagong.util;

import com.authority.job.chittagong.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GeneralUtil extends BasePage {
    public static final int IMPLICIT_WAIT = 5;

    public static WebDriver.Timeouts implicitWait() {
        return driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GeneralUtil.IMPLICIT_WAIT));
    }

    public WebElement waitForElementVisibility(By by) {
        WebDriverWait waitForElementEleStable = new WebDriverWait(driver, Duration.ofSeconds(10));
        return waitForElementEleStable.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForElementClickable(By by) {
        WebDriverWait waitForElementEleStable = new WebDriverWait(driver, Duration.ofSeconds(10));
        return waitForElementEleStable.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForDomStable() throws InterruptedException {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForLongTime() throws InterruptedException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPageTittle() {
        return null;
    }
}
