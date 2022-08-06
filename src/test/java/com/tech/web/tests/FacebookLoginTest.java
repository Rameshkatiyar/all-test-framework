package com.tech.web.tests;

import com.tech.annotations.Testable;
import com.tech.base.WebBaseTest;
import com.tech.constants.TestType;
import com.tech.web.config.WebUrl;
import com.tech.web.pages.FacebookLoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Testable(testName = "FBLogin")
public class FacebookLoginTest extends WebBaseTest {

    private FacebookLoginPage facebookLoginPage;

    @BeforeClass
    public void prep(){
        facebookLoginPage = new FacebookLoginPage(driver);
        web.openWindow(WebUrl.fbLogin);
    }

    @Test(groups = {TestType.SMOKE}, description = "Verify FB login with invalid username and password.")
    public void testLogin(){
        facebookLoginPage.doLogin("xyz@gmail.com", "12345");
    }

    @Test(groups = {TestType.ACCEPTANCE}, description = "Verify FB login with empty username.")
    public void testLoginWithEmptyUsername(){
        web.hitUrlPath(WebUrl.fbLogin);
        facebookLoginPage.doLogin("", "12345");

        String actualErrorMsg = web.getElementText(facebookLoginPage.getErrorBox());
        String expectedErrorMsg = "Wrong credentials\n" + "Invalid username or password";

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }
}
