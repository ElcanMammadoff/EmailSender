package com.EmailSender.EmailSender.controller;

import com.EmailSender.EmailSender.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImages(
            @RequestParam("folder") String folderPath,
            @RequestBody List<String> urls) {

        try {
            imageService.saveImages(folderPath, urls);
            return ResponseEntity.ok("Images uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload images: " + e.getMessage());
        }
    }
}
