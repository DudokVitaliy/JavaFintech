package org.example.services;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private final String uploadDir = "images/large";

    public String downloadFromUrl(String imageUrl) {
        try {
            Path dir = Paths.get(uploadDir);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            String fileName = UUID.randomUUID() + ".jpg";
            Path filePath = dir.resolve(fileName);

            try (InputStream in = new URL(imageUrl).openStream()) {
                Files.copy(in, filePath);
            }

            return fileName;

        } catch (Exception e) {
            throw new RuntimeException("Failed to download image from URL: " + imageUrl, e);
        }
    }
}