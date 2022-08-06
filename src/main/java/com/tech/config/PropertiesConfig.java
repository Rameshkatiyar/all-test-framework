package com.tech.config;

import com.tech.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertiesConfig {
    private static final String propertiesFileName = "/test.properties";
    private static final String propertiesFilePath = FileUtil.getAbsoluteDirPath("properties")
            +propertiesFileName;
    private static Properties properties = new Properties();

    static {
        loadPropertiesFile();
    }

    private static void loadPropertiesFile(){
        InputStream iStream = null;
        try {
            iStream = new FileInputStream(propertiesFilePath);
            properties.load(iStream);
        } catch (IOException e) {
            System.out.println(e);
            log.error(e.toString());
        }finally {
            try {
                if(iStream != null){
                    iStream.close();
                }
            } catch (IOException e) {
                log.error(e.toString());
                System.out.println(e);
            }
        }
    }

    public static String getProp(String key){
        return properties.getProperty(key);
    }
}
