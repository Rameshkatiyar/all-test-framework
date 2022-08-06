package com.tech.web;

import com.tech.annotations.Testable;
import com.tech.base.WebBaseTest;
import com.tech.constants.TestType;
import org.testng.annotations.Test;

@Testable(testName = "LoginPage")
public class LoginPageTest extends WebBaseTest {

    @Test(groups = {TestType.SMOKE})
    public void testLogin(){
        System.out.println("Test1 Login Page...!"+Thread.currentThread().getName());
        driver.get(baseURI);
    }

    @Test
    public void testLogin2(){
        System.out.println("Test2 Login Page...!"+Thread.currentThread().getName());
    }
}
