package com.tech.base;

import com.tech.annotations.Platform;
import com.tech.constants.PlatformType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@Platform(name = PlatformType.APP)
public class AppBaseTest extends BaseTest{
    @BeforeClass
    public void appTestSetup(){
        System.out.println("Before App Test Class!");
    }

    @AfterClass
    public void closeAppTestSetup(){
        System.out.println("After App Test Class!");
    }
}
