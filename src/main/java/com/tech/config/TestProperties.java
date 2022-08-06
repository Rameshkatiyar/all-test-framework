package com.tech.config;

public interface TestProperties {
    String browserStackUrl = PropertiesConfig.getProp("browserStackUrl");
    String webBaseURI = PropertiesConfig.getProp("webBaseURI");
    String apiBaseURI = PropertiesConfig.getProp("apiBaseURI");
    String browserType = PropertiesConfig.getProp("browserType");
    String browserVersion = PropertiesConfig.getProp("browserVersion");
    String osType = PropertiesConfig.getProp("osType");
    String osVersion = PropertiesConfig.getProp("osVersion");
    String webResolution = PropertiesConfig.getProp("webResolution");
    String extentReportUrl = PropertiesConfig.getProp("extentReportUrl");
}
