package com.tech.base;

import com.tech.annotations.Platform;
import com.tech.constants.PlatformType;
import org.testng.annotations.BeforeClass;

@Platform(name = PlatformType.API)
public class ApiBaseTest extends BaseTest{
    @BeforeClass
    public void setup(){
        System.out.println("Before API Test Class!");
    }
}
