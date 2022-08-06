package com.tech.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseWebPage {
    public WebDriver driver;

    public void setDriver(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, getCurrentObject());
    }

    public Object getCurrentObject(){
        return this;
    }
}
