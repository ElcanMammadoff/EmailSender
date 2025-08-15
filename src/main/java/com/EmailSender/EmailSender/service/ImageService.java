package com.EmailSender.EmailSender.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    public void saveImages(String folderPath, List<String> urls) throws Exception {
        // 1️⃣ Create folder if it doesn't exist
        Path folder = Paths.get(folderPath);
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        }

        // 2️⃣ Download and save images from URLs
        if (urls != null) {
            for (String urlString : urls) {
                URL url = new URL(urlString);

                // Extract safe filename from URL path
                String path = url.getPath();
                String originalFileName = path.substring(path.lastIndexOf("/") + 1);

                // Remove query parameters if any
                if (originalFileName.contains("?")) {
                    originalFileName = originalFileName.substring(0, originalFileName.indexOf("?"));
                }

                // If filename is empty, generate random one
                if (originalFileName.isBlank()) {
                    originalFileName = "image_" + UUID.randomUUID() + ".jpg";
                } else {
                    // Append random UUID to avoid duplicates
                    String extension = "";
                    int dotIndex = originalFileName.lastIndexOf(".");
                    if (dotIndex != -1) {
                        extension = originalFileName.substring(dotIndex);
                        originalFileName = originalFileName.substring(0, dotIndex);
                    }
                    originalFileName = originalFileName + "_" + UUID.randomUUID() + extension;
                }

                try (InputStream in = url.openStream();
                     FileOutputStream out = new FileOutputStream(folderPath + File.separator + originalFileName)) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }
        }
    }
}
