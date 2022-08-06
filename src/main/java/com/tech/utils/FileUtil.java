package com.tech.utils;

import java.io.File;

public class FileUtil {
    public static String getAbsoluteDirPath(String dirName){
        File directoryPath = new File(dirName);
        File absoluteFile = directoryPath.getAbsoluteFile();
        return absoluteFile.toString();
    }
}
