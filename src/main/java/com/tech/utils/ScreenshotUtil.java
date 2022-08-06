package com.tech.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

@Slf4j
public class ScreenshotUtil {
    public static void captureScreenshot(WebDriver driver, String fileName){
        String screenshotPath = PathUtil.concatPaths(FileUtil.getAbsoluteDirPath("tempdata"), fileName);
        System.out.println("Took screenshot at path: "+screenshotPath);
        try {
            File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotAs, new File(screenshotPath));
        }catch (Exception e){
            log.error("Error in taking screenshot. {}", e.getMessage());
        }
    }
}
