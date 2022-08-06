package com.tech.service;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.tech.config.TestProperties;
import com.tech.utils.FileUtil;
import com.tech.utils.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class ExtentReportTestNgListener implements ITestListener {
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void createReport(){
        // start reporters
        htmlReporter = new ExtentHtmlReporter("reports/extent.html");

        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void closeReport(){
        extent.flush();
    }

    public static void addFailedScreenshot(String actualImgName,
                                           String expectedImgName,
                                           String diffImgName){
        String imagePath = TestProperties.extentReportUrl;
        try {
            test.addScreenCaptureFromPath(PathUtil.concatPaths(imagePath, "testdata/expectedScreenshots", expectedImgName), "Expected Image");
            test.addScreenCaptureFromPath(PathUtil.concatPaths(imagePath, "tempdata", diffImgName), "Diff Image");
            test.addScreenCaptureFromPath(PathUtil.concatPaths(imagePath, "tempdata", actualImgName), "Actual Image");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getName();
        String description = result.getMethod().getDescription();
        System.out.println("Started Test: "+name);

        // creates a toggle for the given test, adds all log events under it
        this.test = extent.createTest(name, description);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //Taking screenshot
        test.fail(result.getThrowable().toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped");
    }
}

