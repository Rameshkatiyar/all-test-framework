package com.tech.web.tests;

import com.tech.annotations.Testable;
import com.tech.base.WebBaseTest;
import com.tech.constants.TestType;
import com.tech.service.Validator;
import com.tech.web.config.WebUrl;
import com.tech.web.pages.FacebookSignupPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Testable(testName = "FBSignup")
public class SignupPageTest extends WebBaseTest {

    private FacebookSignupPage facebookSignupPage;

    @BeforeClass
    public void prep(){
        facebookSignupPage = new FacebookSignupPage(driver);
        web.openWindow(WebUrl.fbSignup);
    }

    @Test(groups = {TestType.SMOKE}, description = "Verify FB signup page for incomplete details.")
    public void signupTest(){
        web.putElementText(facebookSignupPage.getFirstNameField(), "Ramesh");
        web.putElementText(facebookSignupPage.getLastNameField(), "Katiyar");

        facebookSignupPage.getSignupButton().click();

        String imgName = "invalid-fb-signup-page.png";
        web.takeScreenshot(imgName);

        Validator.isImageSimilar(imgName, imgName);
    }
}
