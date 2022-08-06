package com.tech.api;

import com.tech.annotations.Testable;
import com.tech.base.ApiBaseTest;
import org.testng.annotations.Test;

@Testable(testName = "Login API Test")
public class LoginAPITest extends ApiBaseTest {

    @Test
    public void apiTest1(){
        System.out.println("API Test!"+Thread.currentThread().getName());
    }

    @Test
    public void apiTest2(){
        System.out.println("API Test!"+Thread.currentThread().getName());
    }
}
