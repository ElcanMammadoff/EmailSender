package com.EmailSender.EmailSender.controller;

import com.EmailSender.EmailSender.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/download-instagram")
    public String downloadInstagramImages(@RequestParam String url) {
        String saveFolder = "/Users/elcanmammadov/Downloads/instagram-images";
        imageService.downloadImagesFromInstagram(url, saveFolder);
        return "Download completed!";
    }
}
