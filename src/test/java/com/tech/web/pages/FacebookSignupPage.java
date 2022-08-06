package com.tech.web.pages;

import com.tech.base.BaseWebPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class FacebookSignupPage extends BaseWebPage {
    @FindBy(name = "firstname")
    WebElement firstNameField;

    @FindBy(name = "lastname")
    WebElement lastNameField;

    @FindBy(name = "websubmit")
    WebElement signupButton;

    public FacebookSignupPage(WebDriver driver){
        setDriver(driver);
    }
}
