package com.tech.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class ImageDiffUtil {

    public static boolean isImageSimilar(String firstImgAbsolutePath,
                                         String secondImgAbsolutePath) {
        BufferedImage img1 = readImage(firstImgAbsolutePath);
        BufferedImage img2 = readImage(secondImgAbsolutePath);

        if (null == img1 || null == img2){
            return false;
        }

        int width1 = img1.getWidth();
        int width2 = img2.getWidth();
        int height1 = img1.getHeight();
        int height2 = img2.getHeight();

        if ((width1 != width2) || (height1 != height2)) {
            return false;
        }

        // Modified - Changed to int as pixels are ints
        int diff;
        for (int i = 0; i < height1; i++) {
            for (int j = 0; j < width1; j++) {
                int rgb1 = img1.getRGB(j, i);
                int rgb2 = img2.getRGB(j, i);
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = (rgb1) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = (rgb2) & 0xff;
                diff = Math.abs(r1 - r2); // Change
                diff += Math.abs(g1 - g2);
                diff += Math.abs(b1 - b2);
                diff /= 3; // Change - Ensure result is between 0 - 255
                // Make the difference image gray scale
                // The RGB components are all the same
                if (diff != 0){
                    return false;
                }
            }
        }

        return true;
    }

    public static int getDiffOfImages(String firstImgAbsolutePath,
                                      String secondImgAbsolutePath,
                                      String differenceImgAbsolutePath) {
        BufferedImage img1 = readImage(firstImgAbsolutePath);
        BufferedImage img2 = readImage(secondImgAbsolutePath);

        if (null == img1 || null == img2){
            return 0;
        }

        int width1 = img1.getWidth();
        int width2 = img2.getWidth();
        int height1 = img1.getHeight();
        int height2 = img2.getHeight();

        if ((width1 != width2) || (height1 != height2)) {
            width1 = Math.min(width1, width2);
            height1 = Math.min(height1, height2);
        }

        // NEW - Create output Buffered image of type RGB
        BufferedImage outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

        // Modified - Changed to int as pixels are ints
        long matchCount = 0;
        long missMatchCount = 0;
        int diff;
        int result; // Stores output pixel
        for (int i = 0; i < height1; i++) {
            for (int j = 0; j < width1; j++) {
                int rgb1 = img1.getRGB(j, i);
                int rgb2 = img2.getRGB(j, i);
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = (rgb1) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = (rgb2) & 0xff;
                diff = Math.abs(r1 - r2); // Change
                diff += Math.abs(g1 - g2);
                diff += Math.abs(b1 - b2);
                diff /= 3; // Change - Ensure result is between 0 - 255
                // Make the difference image gray scale
                // The RGB components are all the same
                if (diff != 0){
                    missMatchCount++;
                }else {
                    matchCount++;
                }
                result = (diff << 16) | (diff << 8) | diff;
                outImg.setRGB(j, i, result); // Set result
            }
        }

        int matchPercentage = (int) Math.floorDiv((matchCount*100), (missMatchCount + matchCount));

        // Now copy the difference image at given location.
        writeImage(outImg, differenceImgAbsolutePath, "png");
        return matchPercentage;
    }

    private static BufferedImage readImage(String imagePath){
        BufferedImage img;
        try {
            img =  ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            img = null;
            log.error("Image path is incorrect. {}", e.getMessage());
        }
        return img;
    }

    private static void writeImage(BufferedImage img, String absoluteImagePath, String imageExtension){
        try {
            File outputFile = new File(absoluteImagePath);
            ImageIO.write(img, imageExtension, outputFile);
        } catch (IOException e) {
            log.error("Image is not copied properly at location : {}", absoluteImagePath);
        }
    }
}
