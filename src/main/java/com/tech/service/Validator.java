package com.tech.service;

import com.tech.utils.FileUtil;
import com.tech.utils.ImageDiffUtil;
import com.tech.utils.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
public class Validator {
    public static void isImageSimilar(String actualImgName, String expectedImgName){
        String actualImagePath = PathUtil.concatPaths(FileUtil.getAbsoluteDirPath("tempdata"), actualImgName);
        String expectedImagePath = PathUtil.concatPaths(FileUtil.getAbsoluteDirPath("testdata"), "expectedScreenshots", expectedImgName);
        String diffImageName = "diff-"+expectedImgName;
        String diffImagePath = PathUtil.concatPaths(FileUtil.getAbsoluteDirPath("tempdata"), diffImageName);

        boolean imageSimilar = ImageDiffUtil.isImageSimilar(actualImagePath, expectedImagePath);
        if (!imageSimilar){
            ImageDiffUtil.getDiffOfImages(actualImagePath, expectedImagePath, diffImagePath);
            ExtentReportTestNgListener.addFailedScreenshot(actualImgName, expectedImgName, diffImageName);
        }

        Assert.assertTrue(imageSimilar);
    }
}
