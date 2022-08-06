package com.tech.base;

import com.tech.service.ExtentReportTestNgListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@Slf4j
public abstract class EnvironmentSetup {
    @BeforeSuite(alwaysRun = true)
    public void setupEnv(){
        System.out.println("Before Each Test Suite!");
        ExtentReportTestNgListener.createReport();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown1(){
        System.out.println("Test Suite End!");
        ExtentReportTestNgListener.closeReport();
    }
}
