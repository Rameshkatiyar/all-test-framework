package com.tech.service.web;

import com.tech.base.BaseWebPage;
import com.tech.utils.PathUtil;
import com.tech.utils.ScreenshotUtil;
import com.tech.utils.WaitUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

@Slf4j
public class Web {
    private String baseUri;
    private WebDriver driver;

    public Web(WebDriver driver, String baseUri){
        this.baseUri = baseUri;
        this.driver = driver;
    }

    public void openWindow(String urlPath){
        String url = PathUtil.concatPaths(baseUri, urlPath);
        driver.manage().window().maximize();
        driver.get(url);
        WaitUtil.waitForPageLoad(driver);
        log.info("Browser open with URL: {} and driver: {}", urlPath, driver.toString());
    }

    public void hitUrlPath(String urlPath){
        String url = PathUtil.concatPaths(baseUri, urlPath);
        driver.get(url);
        WaitUtil.waitForPageLoad(driver);
        log.info("Url open with path: {} and driver: {}", urlPath, driver.toString());
    }

    /**
     * Quits this driver, closing every associated window.
     * @return
     */
    public boolean closeBrowser() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed! Driver: {}", driver.toString());
            return true;
        }
        log.info("Driver is null.");
        return false;
    }

    public void backBrowser(){
        driver.navigate().back();
        log.info("Browser back button clicked!");
    }

    public void switchToFirstTab(){
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        if (newTb.size() > 0){
            driver.switchTo().window(newTb.get(0));
        }
    }

    public void closeTheCurrentTab(){
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        if (newTb.size() > 0){
            driver.close(); //Close the current tab.
            driver.switchTo().window(newTb.get(0)); //Switch to first tab.
        }
    }

    public void switchToTab(int tabSequenceNumber){
        int tabNumber = tabSequenceNumber - 1; //Tab number starts from 0.
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        if (newTb.size() > tabNumber){
            driver.switchTo().window(newTb.get(tabNumber));
        }
    }

    public void click(WebElement element){
        try {
            element.click();
            WaitUtil.waitForPageLoad(driver);
        } catch (Exception e){
           log.error("Not able to click on the element. Error: " + e.getMessage());
        }
    }

    public void rightClick(WebElement element){
        try {
            Actions actions = new Actions(driver);
            actions.contextClick(element).perform();
        } catch (Exception e){
            log.error("Not able to right click on the element. Error: " + e.getMessage());
        }
    }

    public void doubleClick(WebElement element){
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform();
        } catch (Exception e){
            log.error("Not able to right click on the element. Error: " + e.getMessage());
        }
    }

    public void takeScreenshot(String screenshotName){
        ScreenshotUtil.captureScreenshot(driver, screenshotName);
    }

    public boolean isElementPresent(WebElement element){
        try{
            WaitUtil.waitTillElementLoad(driver, 10, element);
            return element.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public String getElementText(WebElement element){
        try{
            WaitUtil.waitTillElementLoad(driver, 10, element);
            return element.getText();
        }catch (Exception e){
            return "";
        }
    }

    public void putElementText(WebElement element, String text){
        try{
            WaitUtil.waitTillElementLoad(driver, 10, element);
            element.sendKeys(text);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
