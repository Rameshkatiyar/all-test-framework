package com.tech.base;

import com.tech.annotations.Platform;
import com.tech.config.TestProperties;
import com.tech.constants.PlatformType;
import com.tech.service.web.Web;
import com.tech.service.web.WebDriverService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

@Platform(name = PlatformType.WEB)
public class WebBaseTest extends BaseTest{
    protected WebDriver driver;
    protected Web web;
    protected String baseURI = TestProperties.webBaseURI;
    protected String browserType = TestProperties.browserType.toLowerCase();
    protected Map<String, String> capabilityNameValueMap = new HashMap<>(){{
        put("browser", TestProperties.browserType);
        put("browser_version", TestProperties.browserVersion);
        put("os", TestProperties.osType);
        put("os_version", TestProperties.osVersion);
        put("resolution", TestProperties.webResolution);
    }};

    @BeforeClass(alwaysRun = true)
    public void webTestSetup(){
        WebDriverService webDriverService = new WebDriverService();
        driver = webDriverService.getWebDriver(browserType, capabilityNameValueMap);
        web = new Web(driver, baseURI);
    }

    @AfterClass(alwaysRun = true)
    public void quitWebTestSetup(){
        web.closeBrowser();
    }
}
