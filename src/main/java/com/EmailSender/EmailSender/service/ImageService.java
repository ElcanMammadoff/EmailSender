package com.EmailSender.EmailSender.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class ImageService {

    private static final String CHROME_DRIVER_PATH = "/Users/elcanmammadov/Downloads/chromedriver";

    static {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
    }

    public void downloadImagesFromInstagram(String postUrl, String saveFolder) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // run in background
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(postUrl);

            // Find all <img> elements in the post
            List<WebElement> images = driver.findElements(By.tagName("img"));

            int count = 1;
            for (WebElement img : images) {
                String src = img.getAttribute("src");
                if (src != null && !src.isEmpty()) {
                    downloadImage(src, saveFolder + "/image_" + count + ".jpg");
                    count++;
                }
            }
        } finally {
            driver.quit();
        }
    }

    private void downloadImage(String imageUrl, String destinationFile) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(imageUrl).openStream());
             FileOutputStream out = new FileOutputStream(destinationFile)) {

            byte[] data = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                out.write(data, 0, count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
