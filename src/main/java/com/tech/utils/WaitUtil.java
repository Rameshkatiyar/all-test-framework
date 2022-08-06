package com.tech.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Slf4j
public class WaitUtil {
    private static int PAGE_LOAD_TIME_OUT = 30;
    private static int ELEMENT_LOAD_TIME_OUT = 120;

    /**
     * This will wait till 'document.readyState' value is not 'complete'.
     * This will value marked as complete after full page load only.
     * This is the one type of explicit wait.
     * @param driver
     */
    public static void waitForPageLoad(WebDriver driver) {
        long startTime = 0l;
        long endTime = 0l;

        startTime = System.currentTimeMillis();
        ExpectedCondition<Boolean> pageLoadCondition =
                driver1 -> ((JavascriptExecutor) driver1)
                        .executeScript("return document.readyState")
                        .equals("complete");

        WebDriverWait wait = new WebDriverWait(driver, PAGE_LOAD_TIME_OUT);
        wait.until(pageLoadCondition);

        //Extra check for page load time out.
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
        endTime = System.currentTimeMillis();

        long waitingTime = endTime - startTime;
        log.info("Ended waiting for page load. Total time : {} ms.", waitingTime);
    }

    public static void waitTillTextChange(WebDriver driver, WebElement element, String expectedText) {
        long startTime = 0l;
        long endTime = 0l;

        startTime = System.currentTimeMillis();

        ExpectedCondition<Boolean> expectedCondition =
                ExpectedConditions.textToBePresentInElement(element, expectedText);

        WebDriverWait wait = new WebDriverWait(driver, PAGE_LOAD_TIME_OUT);
        wait.until(expectedCondition);
        endTime = System.currentTimeMillis();

        long waitingTime = endTime - startTime;
        log.info("Ended waiting for text change. Total time : {} ms.", waitingTime);
    }

    public static void wait(int timeInSeconds){
        try{
            Thread.sleep(timeInSeconds*1000);
        }catch (Exception e){

        }
    }

    public static void waitTillElementLoad(WebDriver driver, WebElement... element){
        WebDriverWait wait = new WebDriverWait(driver,ELEMENT_LOAD_TIME_OUT);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static void waitTillElementLoad(WebDriver driver, int timeOutInSeconds,  WebElement... element){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
}
