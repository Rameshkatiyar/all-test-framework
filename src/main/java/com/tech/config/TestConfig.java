package com.tech.config;

public class TestConfig {
    public static String getTestGroup(){
        return System.getProperty("group");
    }

    public static String getTestClass(){
        return System.getProperty("class");
    }

    public static String getTestPlatform(){
        return System.getProperty("platform");
    }

    public static String getTestEnvironment(){
        return System.getProperty("env");
    }
}
