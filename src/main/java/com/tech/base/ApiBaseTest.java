package com.tech.base;

import com.tech.annotations.Platform;
import com.tech.config.TestProperties;
import com.tech.constants.PlatformType;
import com.tech.service.api.Api;
import org.testng.annotations.BeforeClass;

@Platform(name = PlatformType.API)
public class ApiBaseTest extends BaseTest{

    protected String baseURI = TestProperties.apiBaseURI;
    protected Api api;

    @BeforeClass(alwaysRun = true)
    public void apiTestSetup(){
        api = new Api(baseURI);
    }
}
