package com.tech.base;

import com.tech.service.ExtentReportTestNgListener;
import com.tech.service.TestDataService;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

@Slf4j
public abstract class BaseTest extends TestDataProvider {
    @BeforeSuite(alwaysRun = true)
    public void setupEnv(){
        System.out.println("Test execution started!");
        ExtentReportTestNgListener.createReport();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        System.out.println("Test execution completed!");
        ExtentReportTestNgListener.closeReport();
    }
}
