package com.tech.utils;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PathUtil {
    public static boolean isValidPath(String path) {
        if (Strings.isNullOrEmpty(path)){
            return false;
        }
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }

    public static String toValidPath(String path) {
        String pathValue = com.google.common.base.Strings.nullToEmpty(path).trim();
        String REGEX = "^http";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(path);

        if (pathValue.charAt(0) != '/' & !matcher.find()){
            pathValue = "/".concat(pathValue);
        }
        pathValue = pathValue.replaceAll("(?<!https:)(?<!http:)//", "/");
        return pathValue;
    }

    public static String getParentDirectoryPath(String path){
        if (!isValidPath(path)){
            return "";
        }
        try{
            Path pathValue = Paths.get(path);
            Path parentPath = pathValue.getParent();
            return parentPath.toString();
        }catch (Exception e){
            return "";
        }
    }

    public static String getFileNameFromPath(String path){
        if (!isValidPath(path)){
            return "";
        }
        try{
            Path pathValue = Paths.get(path);
            Path fileName = pathValue.getFileName();
            return fileName.toString();
        }catch (Exception e){
            return "";
        }
    }

    public static String concatPaths(String... paths){
        String finalPath = "";
        for (String path : paths) {
            finalPath = finalPath.concat(toValidPath(path));
        }
        return toValidPath(finalPath);
    }
}
