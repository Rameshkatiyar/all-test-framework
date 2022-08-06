package com.tech.web;

import com.tech.annotations.Testable;
import com.tech.base.WebBaseTest;
import com.tech.constants.TestType;
import org.testng.annotations.Test;

@Testable(testName = "SignupPage")
public class SignupPageTest extends WebBaseTest {

    @Test(groups = {TestType.SMOKE})
    public void signupTest(){
        System.out.println("Test1 Signup Page...!"+Thread.currentThread().getName());
        driver.get(baseURI);
    }

    @Test
    public void signupTest2(){
        System.out.println("Test2 Signup Page...!"+Thread.currentThread().getName());
    }
}
