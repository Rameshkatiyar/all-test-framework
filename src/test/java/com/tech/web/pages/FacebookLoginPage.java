package com.tech.web.pages;

import com.tech.base.BaseWebPage;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class FacebookLoginPage extends BaseWebPage {
    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(name = "pass")
    WebElement passwordField;

    @FindBy(id = "loginbutton")
    WebElement loginButton;

    @FindBy(id = "error_box")
    WebElement errorBox;

    public void doLogin(String username, String password){
        emailField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public FacebookLoginPage(WebDriver driver){
        setDriver(driver);
    }
}
